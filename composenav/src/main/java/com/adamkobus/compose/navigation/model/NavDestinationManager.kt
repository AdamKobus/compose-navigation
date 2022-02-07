package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.data.GlobalGraph
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.destination.INavDestination
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.PopDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * This manager tracks any visited destination. Based on this, it builds a database of destinations and updates the [currentDestination].
 * It depends on NavComposable to work properly.
 */
class NavDestinationManager internal constructor() {

    private val _currentDestination = MutableStateFlow<INavDestination?>(null)
    val currentDestination: INavDestination?
        get() = _currentDestination.value

    fun observeCurrentDestination(): Flow<INavDestination?> = _currentDestination

    private val knownDestinations = mutableMapOf<String, INavDestination>()
    private var unrecognizedDestinationRoute: String? = null

    private val logger: NavLogger
        get() = ComposeNavigation.getLogger()

    /**
     * @return a matching destination if it has been visited at least once in your app.
     */
    fun findDestination(route: String): INavDestination? {
        synchronized(knownDestinations) {
            return knownDestinations[route]
        }
    }

    internal fun addDestinationsFromAction(action: NavAction) {
        addToKnownDestinations(action.fromDestination)
        addToKnownDestinations(action.toDestination)
    }

    internal fun addToKnownDestinations(destination: INavDestination) {
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
        synchronized(knownDestinations) {
            knownDestinations.putIfAbsent(route, destination)
            if (route == unrecognizedDestinationRoute) {
                unrecognizedDestinationRoute = null
                updateCurrentDestination(destination)
            }
        }
    }

    internal fun onActionAccepted(action: NavAction) {
        if (action.toDestination is NavGraph) {
            updateCurrentDestination(action.toDestination.startDestination())
        } else if (action.toDestination !is PopDestination && action.toDestination.graph != GlobalGraph) {
            updateCurrentDestination(action.toDestination)
        }
    }

    internal fun onRouteUpdated(route: String?) {
        if (route == null) {
            updateCurrentDestination(null)
        } else {
            knownDestinations[route]?.let {
                unrecognizedDestinationRoute = null
                updateCurrentDestination(it)
            } ?: run {
                unrecognizedDestinationRoute = route
            }
        }
    }

    private fun updateCurrentDestination(destination: INavDestination?) {
        if (_currentDestination.value != destination) {
            _currentDestination.value = destination
            logger.d("Current destination changed to $destination")
        }
    }
}
