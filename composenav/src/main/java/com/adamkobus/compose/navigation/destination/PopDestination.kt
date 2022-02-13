package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.action.PopAction

/**
 * This is a special kind of destination that can be used only as a target of [PopAction]
 */
data class PopDestination(
    override val graph: NavGraph,
    override val route: NavRoute = navRoute(graph.name, path = "__back__", reservedNamesCheck = false)
) : INavDestination {

    /**
     * @return formatted string representation of [PopDestination]
     */
    override fun toString(): String {
        return route.buildRoute()
    }
}

internal fun popDestination(graph: NavGraph): PopDestination = PopDestination(graph = graph)
