package com.adamkobus.compose.navigation.model

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.data.GlobalGraph
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.destination.INavDestination
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
    private val actionBuffer = MutableSharedFlow<NavAction>()

    private val destinationManager: NavDestinationManager
        get() = ComposeNavigation.getNavDestinationManager()

    internal val currentDestination: INavDestination?
        get() = destinationManager.currentDestination

    private val logger: NavLogger
        get() = ComposeNavigation.getLogger()

    init {
        scope.launch {
            actionBuffer.collect {
                processAction(it)
            }
        }
    }

    fun register(graphsOwner: Any, graphs: List<NavGraph>): Flow<NavAction> = actionDispatcher.register(graphsOwner, graphs)

    fun unregister(graphsOwner: Any) = actionDispatcher.unregister(graphsOwner)

    fun postNavAction(navAction: NavAction) {
        scope.launch {
            actionBuffer.emit(navAction)
        }
    }

    private suspend fun processAction(action: NavAction) {
        destinationManager.addDestinationsFromAction(action)

        logger.v("Started processing: $action")
        if (action.toDestination is GlobalGraph) {
            logger.w("Discarded: $action | Navigating to GlobalGraph is not allowed")
            return
        }
        val isActionAllowed = currentDestination?.let {
            navGatekeeper.isNavActionAllowed(it, action)
        } ?: true
        if (!isActionAllowed) {
            logger.v("Action discarded by verifier: $action")
            return
        }

        if (actionDispatcher.dispatchAction(action = action)) {
            logger.v("Action delivered: $action")
            destinationManager.onActionAccepted(action)
        } else {
            logger.w("Failed to deliver action: $action")
        }
    }

    fun onBackStackEntryUpdated(entry: NavBackStackEntry?) {
        val route = entry?.destination?.route
        destinationManager.onRouteUpdated(route)
    }
}
