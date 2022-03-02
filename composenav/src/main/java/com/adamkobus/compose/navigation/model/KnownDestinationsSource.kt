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

    internal fun resolveNavBackStackEntryToDestination(entry: NavBackStackEntry) =
        entry.destination.route?.let {
            resolveRouteToDestination(it)
        } ?: UnknownDestination(entry.destination.id.toString())
}

internal fun NavBackStackEntry.toNavStackEntry(
    knownDestinationsSource: KnownDestinationsSource = ComposeNavigation.getKnownDestinationsSource()
): NavStackEntry {
    val destination = knownDestinationsSource.resolveNavBackStackEntryToDestination(this)
    return NavStackEntry(
        destination = destination,
        arguments = buildArguments(destination, this.arguments)
    )
}

private fun buildArguments(destination: NavDestination, arguments: Bundle?): Map<String, String> =
    destination.route.paramNames.associateWith {
        arguments?.getString(it)!!
    }
