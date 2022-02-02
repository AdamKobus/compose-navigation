package com.adamkobus.compose.navigation.data

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

    operator fun plus(other: INavDestination): NavAction = NavAction(this, other)

    infix fun to(other: INavDestination): NavAction {
        return NavAction(this, other)
    }

    fun next(init: NavRoute.Builder.() -> Unit = {}): INavDestination
}
