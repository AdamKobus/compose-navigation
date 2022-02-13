package com.adamkobus.compose.navigation.destination

/**
 * Represents a destination that is displayed as a view in the application
 *
 * @param graph Graph that this destination is assigned to
 * @param route Unique route that identifies this destination
 */
open class ScreenDestination(
    override val graph: NavGraph,
    override val route: NavRoute
) : NavDestination {

    /**
     * Creates a new [ScreenDestination] with a route that is a continuation of this destination's [route]
     */
    fun next(init: NavRoute.Builder.() -> Unit) = ScreenDestination(graph = graph, route = route.next(init = init))

    /**
     * @return formatted String representation of [ScreenDestination]
     */
    override fun toString(): String {
        return "Screen(${route.buildRoute()})"
    }

    /**
     * Compares with other destination based on [graph] and [route] fields
     */
    override fun equals(other: Any?): Boolean {
        return other is ScreenDestination && other.graph == graph && other.route == route
    }

    /**
     * Generates hash code based on [graph] and [route] fields
     */
    override fun hashCode(): Int {
        var result = graph.hashCode()
        result = 31 * result + route.hashCode()
        return result
    }

    /**
     * Converts this destination to [DialogDestination]
     */
    fun asDialog(): DialogDestination = DialogDestination(graph, route)
}

internal fun screenDestination(
    graph: NavGraph,
    pathName: String,
    reservedNameCheck: Boolean = true,
    init: NavRoute.Builder.() -> Unit = {}
): ScreenDestination {
    val route = navRoute(graphName = graph.name, path = pathName, reservedNamesCheck = reservedNameCheck, init)
    return ScreenDestination(graph, route)
}
