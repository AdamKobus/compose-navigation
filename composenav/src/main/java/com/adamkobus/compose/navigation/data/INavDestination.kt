package com.adamkobus.compose.navigation.data

interface INavDestination {

    /**
     * Graph to which this destination belongs
     */
    val graph: NavGraph

    /**
     * Base name of the destination
     */
    val name: String

    /**
     * Builds a full route definition for this destination.
     * By default it will build a route in format <graph.name>/<name>.
     *
     * For more complex paths it should be overridden.
     */
    val route: NavRoute

    operator fun plus(other: INavDestination): NavAction = NavAction(this, other)
}
