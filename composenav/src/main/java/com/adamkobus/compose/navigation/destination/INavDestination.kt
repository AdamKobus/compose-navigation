package com.adamkobus.compose.navigation.destination

/**
 * Base type for all of the destinations.
 */
interface INavDestination {
    /**
     * Graph that this destination belongs to
     */
    val graph: NavGraph

    /**
     * Each destination must have a unique route.
     */
    val route: NavRoute
}
