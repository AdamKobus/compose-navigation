package com.adamkobus.compose.navigation.model

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.data.GlobalGraph
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.destination.INavDestination
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.PopDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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

    private val _currentDestination = MutableStateFlow<INavDestination?>(null)
    private var undecognizedDestinationRoute: String? = null

    val currentDestination: INavDestination?
        get() = _currentDestination.value

    private val knownDestinations = mutableMapOf<String, INavDestination>()

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
        log("processing $action started")
        val isActionAllowed = currentDestination?.let {
            navGatekeeper.isNavActionAllowed(it, action)
        } ?: true
        if (!isActionAllowed) {
            log("$action discarded")
            return
        }
        registerDestination(action.fromDestination)
        registerDestination(action.toDestination)

        if (action.toDestination !is PopDestination && action.toDestination.graph != GlobalGraph) {
            _currentDestination.value = action.toDestination
        }
        actionDispatcher.dispatchAction(action = action)
        log("processing $action finished")
    }

    private fun registerDestination(destination: INavDestination) {
        if (destination is NavGraph) {
            val startDestination = destination.startDestination()
            addKnownDestination(startDestination.route.buildRoute(), startDestination)
        } else if (destination is NavDestination) {
            addKnownDestination(destination.route.buildRoute(), destination)
            val startDestination = destination.graph.startDestination()
            addKnownDestination(startDestination.route.buildRoute(), startDestination)
        }
    }

    private fun addKnownDestination(route: String, destination: INavDestination) {
        knownDestinations.putIfAbsent(route, destination)
        if (route == undecognizedDestinationRoute) {
            undecognizedDestinationRoute = null
            _currentDestination.value = destination
        }
    }

    @Suppress("UnusedPrivateMember")
    private fun log(message: String) {
        // TODO add logger
    }

    fun onBackStackEntryUpdated(entry: NavBackStackEntry?) {
        val route = entry?.destination?.route
        if (route == null) {
            _currentDestination.value = null
        } else {
            knownDestinations[route]?.let {
                undecognizedDestinationRoute = null
                _currentDestination.value = it
            } ?: run {
                undecognizedDestinationRoute = route
            }
        }
    }
}
