package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.action.NavigateAction
import com.adamkobus.compose.navigation.action.PopAction
import com.adamkobus.compose.navigation.data.NavGraph

data class DialogDestination(
    override val graph: NavGraph,
    override val route: NavRoute
) : INavDestination {

    operator fun plus(other: PopDestination): PopAction = to(other)

    infix fun to(other: PopDestination): PopAction = PopAction(this, other)

    operator fun plus(other: NavDestination): NavigateAction = to(other)

    infix fun to(other: NavDestination) = NavigateAction(this, other)

    operator fun plus(other: NavGraph): NavigateAction = to(other)

    infix fun to(other: NavGraph): NavigateAction = NavigateAction(this, other)

    operator fun plus(other: DialogDestination) = NavigateAction(this, other)

    infix fun to(other: DialogDestination) = NavigateAction(this, other)
}

internal fun dialogDestination(
    graph: NavGraph,
    pathName: String,
    reservedNameCheck: Boolean = true,
    init: NavRoute.Builder.() -> Unit = {}
): DialogDestination {
    val route = navRoute(graphName = graph.name, path = pathName, reservedNamesCheck = reservedNameCheck, init)
    return DialogDestination(graph, route)
}
