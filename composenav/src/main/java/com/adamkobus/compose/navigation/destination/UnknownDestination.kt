package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.NavigationStateSource

/**
 * Represents a destination that was not recognized by [NavigationStateSource].
 *
 * @param path Raw path string that was encountered by [NavigationStateSource]
 */
data class UnknownDestination internal constructor(
    val path: String,
) : NavDestination {
    /**
     * Fixed value - [UnknownGraph]
     */
    override val graph: NavGraph = UnknownGraph

    /**
     * Navigating to this route will most likely produce crash.
     * This destination is purely for informational purpose and should not be navigated to.
     * The actual path that was encountered can be accessed via [path] param
     */
    override val route: NavRoute = navRoute(graphName = UnknownGraph.name, path = path, reservedNamesCheck = false)

    /**
     * @return formatted String representation of [UnknownDestination]
     */
    override fun toString(): String = "Unknown($path)"
}
