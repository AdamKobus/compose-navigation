package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.data.NavGraph

interface INavDestination {

    /**
     * Graph to which this destination belongs
     */
    val graph: NavGraph

    /**
     * Builds a full route definition for this destination.
     * By default it will build a route in format <graph.name>/<name>.
     *
     * For more complex paths it should be overridden.
     */
    val route: NavRoute
}
