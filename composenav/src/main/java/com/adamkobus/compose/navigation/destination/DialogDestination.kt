package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.data.NavGraph

data class DialogDestination(
    override val graph: NavGraph,
    override val route: NavRoute
) : INavDestination

internal fun dialogDestination(
    graph: NavGraph,
    pathName: String,
    reservedNameCheck: Boolean = true,
    init: NavRoute.Builder.() -> Unit = {}
): DialogDestination {
    val route = navRoute(graphName = graph.name, path = pathName, reservedNamesCheck = reservedNameCheck, init)
    return DialogDestination(graph, route)
}
