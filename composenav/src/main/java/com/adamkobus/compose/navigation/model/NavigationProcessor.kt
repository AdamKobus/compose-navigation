package com.adamkobus.compose.navigation.model

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.data.GlobalGraph
import com.adamkobus.compose.navigation.data.NavGraph
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
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NavigationProcessor @Inject constructor(
    private val navGatekeeper: NavGatekeeper,
    private val actionDispatcher: PendingActionDispatcher
) {
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

    init {
        scope.launch {
            actionBuffer.collect {
                processTask(it)
            }
        }
    }

    fun register(graphsOwner: Any, graphs: List<NavGraph>): Flow<NavAction> = actionDispatcher.register(graphsOwner, graphs)

    fun unregister(graphsOwner: Any) = actionDispatcher.unregister(graphsOwner)

    fun postNavAction(navAction: NavAction) {
        scope.launch {
            actionBuffer.emit(ProcessorTask.Action(navAction))
        }
    }

    fun postNavIntent(intent: NavIntent) {
        scope.launch {
            actionBuffer.emit(ProcessorTask.Intent(intent))
        }
    }

    private suspend fun processTask(task: ProcessorTask) {
        when (task) {
            is ProcessorTask.Action -> processAction(task.navAction)
            is ProcessorTask.Intent -> processIntent(task.navIntent)
        }
    }

    private suspend fun processIntent(navIntent: NavIntent) {
        val action = navIntentResolver.resolve(navIntent, currentDestination)
        if (action == null) {
            logger.w("Intent $navIntent discarded, because it was not mapped to any action")
        } else {
            processAction(action)
        }
    }

    private suspend fun processAction(action: NavAction) {
        destinationManager.addDestinationsFromAction(action)

        logger.v("Started processing: $action")
        if (action.toDestination is GlobalGraph) {
            logger.w("Discarded: $action | Navigating to GlobalGraph is not allowed")
            return
        }
        val isActionAllowed = navGatekeeper.isNavActionAllowed(currentDestination, action)
        if (!isActionAllowed) {
            logger.v("Action discarded by verifier: $action")
            return
        }
        onActionPerformed = CompletableDeferred()
        if (actionDispatcher.dispatchAction(action = action)) {
            logger.v("Action delivered: $action")
            onActionPerformed?.await()
            logger.v("Stopped processing: $action")
        } else {
            logger.w("Failed to deliver action: $action")
        }
    }

    fun onBackStackEntryUpdated(entry: NavBackStackEntry?, backQueue: List<NavBackStackEntry>) {
        destinationManager.onBackStackUpdated(entry, backQueue)
        onActionPerformed?.complete(Unit)
        onActionPerformed = null
    }
}

private sealed class ProcessorTask {

    class Action(val navAction: NavAction) : ProcessorTask() {
        override fun equals(other: Any?): Boolean {
            return other is Action && other.navAction == navAction
        }

        override fun hashCode(): Int {
            return navAction.hashCode()
        }
    }

    class Intent(val navIntent: NavIntent) : ProcessorTask() {
        override fun equals(other: Any?): Boolean {
            return other is Intent && other.navIntent == navIntent
        }

        override fun hashCode(): Int {
            return navIntent.hashCode()
        }
    }
}
