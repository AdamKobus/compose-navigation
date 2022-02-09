package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.data.NavGraph

open class NavDestination(
    override val graph: NavGraph,
    override val route: NavRoute
) : INavDestination {

    override fun toString(): String {
        return "Destination(${route.buildRoute()})"
    }

    override fun equals(other: Any?): Boolean {
        return other is NavDestination && other.graph == graph && other.route == route
    }

    override fun hashCode(): Int {
        var result = graph.hashCode()
        result = 31 * result + route.hashCode()
        return result
    }
}

internal fun navDestination(
    graph: NavGraph,
    pathName: String,
    reservedNameCheck: Boolean = true,
    init: NavRoute.Builder.() -> Unit = {}
): NavDestination {
    val route = navRoute(graphName = graph.name, path = pathName, reservedNamesCheck = reservedNameCheck, init)
    return NavDestination(graph, route)
}
