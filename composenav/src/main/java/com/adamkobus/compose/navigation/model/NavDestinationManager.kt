package com.adamkobus.compose.navigation.model

import android.os.Bundle
import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationStateSource
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.destination.INavDestination
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.NavStackEntry
import com.adamkobus.compose.navigation.destination.NavState
import com.adamkobus.compose.navigation.destination.UnknownDestination
import com.adamkobus.compose.navigation.logger.NavLogger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * This manager tracks any visited destination. Based on this, it builds a database of destinations and updates the [navState].
 * It depends on NavComposable to work properly.
 */
internal class NavDestinationManager : NavigationStateSource {

    private val _navState = MutableStateFlow(NavState(null, emptyList()))
    override val navState: NavState
        get() = _navState.value

    override fun observeNavState(): StateFlow<NavState> = _navState

    private val knownDestinations = mutableMapOf<String, NavDestination>()

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
        if (destination is NavDestination) {
            val startDestination = destination.graph.startDestination()
            addKnownDestination(startDestination.route.buildRoute(), startDestination)
            addKnownDestination(destination.route.buildRoute(), destination)
        }
    }

    private fun addKnownDestination(route: String, destination: NavDestination) {
        synchronized(knownDestinations) {
            knownDestinations.putIfAbsent(route, destination)
        }
    }

    internal fun onBackStackUpdated(entry: NavBackStackEntry?, backQueue: List<NavBackStackEntry>) {
        if (entry == null) {
            updateCurrentDestination(NavState(null, emptyList()))
        } else {
            val backStack = backQueue.mapNotNull {
                it.toNavStackEntry(this)
            }
            updateCurrentDestination(NavState(entry.toNavStackEntry(this), backStack))
        }
    }

    internal fun resolveRouteToDestination(route: String?): NavDestination? =
        route?.let {
            knownDestinations[route] ?: UnknownDestination(it)
        }

    private fun updateCurrentDestination(destination: NavState) {
        if (_navState.value != destination) {
            _navState.value = destination
            logger.d("Current destination changed to $destination")
        }
    }
}

internal fun NavBackStackEntry.toNavStackEntry(
    manager: NavDestinationManager = ComposeNavigation.getNavDestinationManager()
): NavStackEntry? =
    this.let { entry ->
        entry.destination.route.let { route ->
            manager.resolveRouteToDestination(route)
        }?.let { destination ->
            NavStackEntry(
                destination = destination,
                arguments = buildArguments(destination, entry.arguments)
            )
        }
    }

private fun buildArguments(destination: NavDestination, arguments: Bundle?): Map<String, String> =
    destination.route.paramNames.associateWith {
        arguments?.getString(it)!!
    }
