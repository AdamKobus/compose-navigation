package com.adamkobus.compose.navigation.data

data class NavDestination(
    override val graph: NavGraph,
    override val name: String = "",
    override val route: NavRoute = navRoute(graph.name, name)
) : INavDestination {

    fun next(init: NavRoute.Builder.() -> Unit = {}): NavDestination {
        return NavDestination(graph = graph, name = name, route = route.next(init = init))
    }

    override fun toString(): String {
        return route.buildRoute()
    }
}
