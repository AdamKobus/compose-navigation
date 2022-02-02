package com.adamkobus.compose.navigation.data

interface NavGraph : INavDestination {
    val name: String

    fun navDestination(pathName: String, init: NavRoute.Builder.() -> Unit = {}): INavDestination =
        navDestination(this, pathName = pathName, init = init)

    override val graph: NavGraph
        get() = this

    override val route: NavRoute
        get() = navRoute(name)

    override fun next(init: NavRoute.Builder.() -> Unit): INavDestination = NavDestination(graph = graph, route = route.next(init = init))
}
