package com.adamkobus.compose.navigation.model

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationId
import com.adamkobus.compose.navigation.action.DiscardReason
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavigationResult
import com.adamkobus.compose.navigation.destination.GlobalGraph
import com.adamkobus.compose.navigation.destination.NavState
import com.adamkobus.compose.navigation.intent.NavIntent
import com.adamkobus.compose.navigation.intent.NavIntentResolvingManager
import com.adamkobus.compose.navigation.logger.NavLogger
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

internal class NavigationProcessor(val navigationId: NavigationId) {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val actionBuffer = MutableSharedFlow<ProcessorTask>()

    private var onActionPerformed: CompletableDeferred<Unit>? = null

    private val stateManager: NavStateManager
        get() = ComposeNavigation.getNavDestinationManager(navigationId)

    private val knownDestinationsSource: KnownDestinationsSource
        get() = ComposeNavigation.getKnownDestinationsSource()

    private val navState: NavState
        get() = stateManager.navState

    private val navIntentResolver: NavIntentResolvingManager
        get() = ComposeNavigation.getNavIntentResolvingManager()

    private val logger: NavLogger
        get() = ComposeNavigation.getLogger()

    private val navGatekeeper: NavGatekeeper
        get() = ComposeNavigation.getNavGatekeeper()

    private val actionDispatcher: PendingActionDispatcher
        get() = ComposeNavigation.getPendingActionDispatcher()

    private val logPrefix = "[$navigationId]"

    init {
        scope.launch {
            actionBuffer.collect {
                processTask(it)
            }
        }
    }

    fun register(actionConsumer: ActionConsumer): Flow<NavAction> = actionDispatcher.register(actionConsumer)

    fun unregister(actionConsumer: ActionConsumer) = actionDispatcher.unregister(actionConsumer)

    fun postNavAction(navAction: NavAction, onTaskCompleted: CompletableDeferred<NavigationResult>? = null) {
        scope.launch {
            actionBuffer.emit(ProcessorTask.Action(navAction, onTaskCompleted))
        }
    }

    fun postNavIntent(intent: NavIntent, onTaskCompleted: CompletableDeferred<NavigationResult>? = null) {
        scope.launch {
            actionBuffer.emit(ProcessorTask.Intent(intent, onTaskCompleted))
        }
    }

    private suspend fun processTask(task: ProcessorTask) {
        val result = when (task) {
            is ProcessorTask.Action -> processAction(task.navAction)
            is ProcessorTask.Intent -> processIntent(task.navIntent)
        }
        task.onTaskCompleted?.complete(result)
    }

    private suspend fun processIntent(navIntent: NavIntent): NavigationResult {
        val action = navIntentResolver.resolve(navIntent, navState)
        return if (action == null) {
            logger.w("$logPrefix Intent $navIntent was discarded, because it was not mapped to any action")
            NavigationResult.Discarded(DiscardReason.NotMapped)
        } else {
            processAction(action)
        }
    }

    @Suppress("ReturnCount") // TODO clean up
    private suspend fun processAction(action: NavAction): NavigationResult {
        knownDestinationsSource.addDestinationsFromAction(action)

        logger.v("$logPrefix Started processing: $action")
        if (action.toDestination is GlobalGraph) {
            logger.e("$logPrefix Discarded: $action | Navigating to GlobalGraph is not allowed")
            return NavigationResult.Discarded(DiscardReason.NotDelivered)
        }
        val rejectingVerifier = navGatekeeper.isNavActionAllowed(navState, action)
        if (rejectingVerifier != null) {
            logger.v("$logPrefix Action discarded by verifier: $action")
            return NavigationResult.Discarded(DiscardReason.RejectedByVerifier(rejectingVerifier))
        }
        onActionPerformed = CompletableDeferred()
        return if (actionDispatcher.dispatchAction(action = action)) {
            logger.v("$logPrefix Action delivered: $action")
            onActionPerformed?.await()
            logger.v("$logPrefix Stopped processing: $action")
            NavigationResult.Accepted
        } else {
            logger.w("$logPrefix Failed to deliver action: $action")
            NavigationResult.Discarded(DiscardReason.NotDelivered)
        }
    }

    fun onBackStackEntryUpdated(entry: NavBackStackEntry?, backQueue: List<NavBackStackEntry>) {
        stateManager.onBackStackUpdated(entry, backQueue)
        onActionPerformed?.complete(Unit)
        onActionPerformed = null
    }

    fun onActionCompletedWithoutBackStackUpdate() {
        onActionPerformed?.complete(Unit)
        onActionPerformed = null
    }
}

private sealed class ProcessorTask(val onTaskCompleted: CompletableDeferred<NavigationResult>?) {

    class Action(val navAction: NavAction, onTaskCompleted: CompletableDeferred<NavigationResult>?) : ProcessorTask(onTaskCompleted) {
        override fun equals(other: Any?): Boolean {
            return other is Action && other.navAction == navAction
        }

        override fun hashCode(): Int {
            return navAction.hashCode()
        }
    }

    class Intent(val navIntent: NavIntent, onTaskCompleted: CompletableDeferred<NavigationResult>?) : ProcessorTask(onTaskCompleted) {
        override fun equals(other: Any?): Boolean {
            return other is Intent && other.navIntent == navIntent
        }

        override fun hashCode(): Int {
            return navIntent.hashCode()
        }
    }
}
