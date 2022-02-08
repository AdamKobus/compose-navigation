package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.data.UnknownGraph

class UnknownDestination internal constructor(val path: String) : INavDestination {
    override val graph: NavGraph = UnknownGraph

    override val route: NavRoute = navRoute(graphName = graph.name, path = path, reservedNamesCheck = false)

    override fun toString(): String = "Unknown($path)"
}
