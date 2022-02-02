package com.adamkobus.compose.navigation.data

data class NavDestination(
    override val graph: NavGraph,
    override val route: NavRoute
) : INavDestination {

    fun next(init: NavRoute.Builder.() -> Unit = {}): NavDestination {
        return NavDestination(graph = graph, route = route.next(init = init))
    }

    override fun toString(): String {
        return route.buildRoute()
    }
}

fun navDestination(graph: NavGraph, pathName: String, init: NavRoute.Builder.() -> Unit = {}): NavDestination {
    val route = navRoute(graphName = graph.name, path = pathName, init)
    return NavDestination(graph, route)
}

fun navDestination(graph: NavGraph): NavDestination {
    val route = navRoute(graphName = graph.name)
    return NavDestination(graph, route)
}
