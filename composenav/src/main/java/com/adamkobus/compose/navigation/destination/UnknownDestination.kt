package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.data.UnknownGraph

class UnknownDestination internal constructor(
    val path: String
) : NavDestination(
    UnknownGraph,
    navRoute(graphName = UnknownGraph.name, path = path, reservedNamesCheck = false)
) {
    override fun toString(): String = "Unknown($path)"
}
