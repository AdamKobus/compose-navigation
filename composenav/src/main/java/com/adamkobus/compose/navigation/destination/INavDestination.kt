package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.action.NavigateAction
import com.adamkobus.compose.navigation.action.PopAction
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

    fun next(init: NavRoute.Builder.() -> Unit) = NavDestination(graph = graph, route = route.next(init = init))

    infix fun pop(other: PopDestination): PopAction = PopAction(this, other)

    infix fun goTo(other: INavDestination) = NavigateAction(this, other)
}
