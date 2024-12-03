package com.adamkobus.compose.navigation.destination

/**
 * Represents a destination that is displayed as a dialog in the application
 *
 * @param graph Graph that this destination is assigned to
 * @param route Unique route that identifies this destination
 */
open class DialogDestination(
    override val graph: NavGraph,
    override val route: NavRoute,
) : NavDestination {
    /**
     * @return formatted String representation of [DialogDestination]
     */
    override fun toString(): String {
        return "Dialog(${route.buildRoute()})"
    }

    /**
     * Compares other [DialogDestination] by [graph] and [route] fields.
     */
    override fun equals(other: Any?): Boolean {
        return other is DialogDestination && other.graph == graph && other.route == route
    }

    /**
     * Builds hash code based on [graph] and [route] fields.
     */
    override fun hashCode(): Int {
        var result = graph.hashCode()
        result = 31 * result + route.hashCode()
        return result
    }
}

internal fun dialogDestination(
    graph: NavGraph,
    pathName: String,
    reservedNameCheck: Boolean = true,
    init: NavRoute.Builder.() -> Unit = {},
): DialogDestination {
    val route = navRoute(graphName = graph.name, path = pathName, reservedNamesCheck = reservedNameCheck, init)
    return DialogDestination(graph, route)
}
