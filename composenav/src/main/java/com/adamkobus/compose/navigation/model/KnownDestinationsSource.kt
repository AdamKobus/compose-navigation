package com.adamkobus.compose.navigation.model

import android.os.Bundle
import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.destination.INavDestination
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.NavStackEntry
import com.adamkobus.compose.navigation.destination.UnknownDestination

internal class KnownDestinationsSource {

    private val knownDestinations = mutableMapOf<String, NavDestination>()

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

    internal fun resolveRouteToDestination(route: String): NavDestination =
        route.let {
            knownDestinations[route] ?: UnknownDestination(it)
        }
}

internal fun NavBackStackEntry.toNavStackEntry(
    knownDestinationsSource: KnownDestinationsSource = ComposeNavigation.getKnownDestinationsSource()
): NavStackEntry =
    this.let { entry ->
        // Compose library is setting routes for all of the destinations, so there is no risk that this would be null.
        entry.destination.route!!.let { route ->
            knownDestinationsSource.resolveRouteToDestination(route)
        }.let { destination ->
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
