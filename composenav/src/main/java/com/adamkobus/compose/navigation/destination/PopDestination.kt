package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.data.NavGraph

data class PopDestination(
    override val graph: NavGraph,
    override val route: NavRoute = navRoute(graph.name, path = "__back__", reservedNamesCheck = false)
) : INavDestination {

    override fun toString(): String {
        return route.buildRoute()
    }
}

internal fun popDestination(graph: NavGraph): PopDestination = PopDestination(graph = graph)
