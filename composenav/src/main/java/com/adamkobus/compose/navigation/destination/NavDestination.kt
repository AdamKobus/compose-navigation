package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.action.NavigateAction
import com.adamkobus.compose.navigation.action.PopAction
import com.adamkobus.compose.navigation.data.NavGraph

data class NavDestination(
    override val graph: NavGraph,
    override val route: NavRoute
) : INavDestination {

    fun next(init: NavRoute.Builder.() -> Unit) = NavDestination(graph = graph, route = route.next(init = init))

    override fun toString(): String {
        return route.buildRoute()
    }

    operator fun plus(other: PopDestination): PopAction = to(other)

    infix fun to(other: PopDestination): PopAction = PopAction(this, other)

    operator fun plus(other: NavDestination): NavigateAction = to(other)

    infix fun to(other: NavDestination) = NavigateAction(this, other)

    operator fun plus(other: NavGraph): NavigateAction = to(other)

    infix fun to(other: NavGraph): NavigateAction = NavigateAction(this, other)
}

internal fun navDestination(graph: NavGraph, pathName: String, init: NavRoute.Builder.() -> Unit = {}): NavDestination {
    val route = navRoute(graphName = graph.name, path = pathName, init)
    return NavDestination(graph, route)
}
