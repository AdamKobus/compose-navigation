package com.adamkobus.compose.navigation.model

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.data.DiscardReason
import com.adamkobus.compose.navigation.data.GlobalGraph
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.data.NavigationResult
import com.adamkobus.compose.navigation.destination.CurrentDestination
import com.adamkobus.compose.navigation.intent.NavIntent
import com.adamkobus.compose.navigation.intent.NavIntentResolvingManager
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

internal class NavigationProcessor {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val actionBuffer = MutableSharedFlow<ProcessorTask>()

    private var onActionPerformed: CompletableDeferred<Unit>? = null

    private val destinationManager: NavDestinationManager
        get() = ComposeNavigation.getNavDestinationManager()

    private val currentDestination: CurrentDestination
        get() = destinationManager.currentDestination

    private val navIntentResolver: NavIntentResolvingManager
        get() = ComposeNavigation.getNavIntentResolvingManager()

    private val logger: NavLogger
        get() = ComposeNavigation.getLogger()

    private val navGatekeeper: NavGatekeeper
        get() = ComposeNavigation.getNavGatekeeper()

    private val actionDispatcher: PendingActionDispatcher
        get() = ComposeNavigation.getPendingActionDispatcher()

    init {
        scope.launch {
            actionBuffer.collect {
                processTask(it)
            }
        }
    }

    fun register(graphsOwner: Any, graphs: List<NavGraph>): Flow<NavAction> = actionDispatcher.register(graphsOwner, graphs)

    fun unregister(graphsOwner: Any) = actionDispatcher.unregister(graphsOwner)

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
        val action = navIntentResolver.resolve(navIntent, currentDestination)
        return if (action == null) {
            logger.w("Intent $navIntent was discarded, because it was not mapped to any action")
            NavigationResult.Discarded(DiscardReason.NotMapped)
        } else {
            processAction(action)
        }
    }

    @Suppress("ReturnCount") // TODO clean up
    private suspend fun processAction(action: NavAction): NavigationResult {
        destinationManager.addDestinationsFromAction(action)

        logger.v("Started processing: $action")
        if (action.toDestination is GlobalGraph) {
            logger.e("Discarded: $action | Navigating to GlobalGraph is not allowed")
            return NavigationResult.Discarded(DiscardReason.NotDelivered)
        }
        val rejectingVerifier = navGatekeeper.isNavActionAllowed(currentDestination, action)
        if (rejectingVerifier != null) {
            logger.v("Action discarded by verifier: $action")
            return NavigationResult.Discarded(DiscardReason.RejectedByVerifier(rejectingVerifier))
        }
        onActionPerformed = CompletableDeferred()
        if (actionDispatcher.dispatchAction(action = action)) {
            logger.v("Action delivered: $action")
            onActionPerformed?.await()
            logger.v("Stopped processing: $action")
            return NavigationResult.Accepted
        } else {
            logger.w("Failed to deliver action: $action")
            return NavigationResult.Discarded(DiscardReason.NotDelivered)
        }
    }

    fun onBackStackEntryUpdated(entry: NavBackStackEntry?, backQueue: List<NavBackStackEntry>) {
        destinationManager.onBackStackUpdated(entry, backQueue)
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
