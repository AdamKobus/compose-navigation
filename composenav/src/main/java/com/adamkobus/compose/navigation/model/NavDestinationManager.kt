package com.adamkobus.compose.navigation.model

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationStateSource
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.destination.CurrentDestination
import com.adamkobus.compose.navigation.destination.DialogDestination
import com.adamkobus.compose.navigation.destination.INavDestination
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.UnknownDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * This manager tracks any visited destination. Based on this, it builds a database of destinations and updates the [currentDestination].
 * It depends on NavComposable to work properly.
 */
internal class NavDestinationManager : NavigationStateSource {

    private val _currentDestination = MutableStateFlow(CurrentDestination(null, emptyList()))
    override val currentDestination: CurrentDestination
        get() = _currentDestination.value

    override fun observeCurrentDestination(): Flow<CurrentDestination> = _currentDestination

    private val knownDestinations = mutableMapOf<String, INavDestination>()

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
            addKnownDestination(destination.route.buildRoute(), destination)
        } else if (destination is NavDestination || destination is DialogDestination) {
            addKnownDestination(destination.route.buildRoute(), destination)
            val startDestination = destination.graph.startDestination()
            addKnownDestination(startDestination.route.buildRoute(), startDestination)
            addKnownDestination(destination.graph.route.buildRoute(), destination.graph)
        }
    }

    private fun addKnownDestination(route: String, destination: INavDestination) {
        synchronized(knownDestinations) {
            knownDestinations.putIfAbsent(route, destination)
        }
    }

    internal fun onBackStackUpdated(entry: NavBackStackEntry?, backQueue: List<NavBackStackEntry>) {
        if (entry == null) {
            updateCurrentDestination(CurrentDestination(null, emptyList()))
        } else {
            val currentDest = resolveRouteToDestination(entry.destination.route)
            val backStack = backQueue.map {
                resolveRouteToDestination(it.destination.route)
            }.filterNotNull()
            updateCurrentDestination(CurrentDestination(currentDest, backStack))
        }
    }

    private fun resolveRouteToDestination(route: String?): INavDestination? =
        route?.let {
            knownDestinations[route] ?: UnknownDestination(it)
        }

    private fun updateCurrentDestination(destination: CurrentDestination) {
        if (_currentDestination.value != destination) {
            _currentDestination.value = destination
            logger.d("Current destination changed to $destination")
        }
    }
}
