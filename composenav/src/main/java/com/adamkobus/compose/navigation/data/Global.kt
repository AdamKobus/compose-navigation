package com.adamkobus.compose.navigation.data

val GlobalGraph = object : NavGraph {
    override val name: String = "global"
}

val GlobalDestination = object : INavDestination {
    override val graph: NavGraph = GlobalGraph

    override val name: String = "any"
    override val route: NavRoute = navRoute(GlobalGraph.name, name)
}

val PopBackStackDestination = object : INavDestination {
    override val graph: NavGraph = GlobalGraph
    override val name: String = "back"
    override val route: NavRoute = navRoute(GlobalGraph.name, name)
}
