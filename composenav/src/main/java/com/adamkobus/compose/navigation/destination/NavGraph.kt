package com.adamkobus.compose.navigation.destination

/**
 * [NavGraph]s are used to group [ScreenDestination]s. Also, each NavHost must be initialized with following information:
 * - graph's name - provided via [name]
 * - start destination - provided via [startDestination]
 *
 * @param name It is used to identify this graph. All destinations created in this graph will also be prefixed by the graph's name.
 */
abstract class NavGraph internal constructor(
    val name: String,
    private val reservedNameCheck: Boolean = true
) : NavDestination {

    /**
     * @param name It is used to identify this graph. All destinations created in this graph will also be prefixed by the graph's name.
     */
    constructor(name: String) : this(name, reservedNameCheck = true)

    /**
     * Represents a route that will be used to register this graph inside NavHost
     *
     * @see [NavRoute]
     */
    override val route: NavRoute = navRoute(name, reservedNamesCheck = reservedNameCheck)

    /**
     * Each graph must have a start destination which NavHostController will try to display when you navigate to this graph.
     * It's ok to have another graph as start destination, but eventually one of the graphs should return [ScreenDestination].
     */
    abstract fun startDestination(): NavDestination

    /**
     * Creates new type-safe builder for [ScreenDestination]. It will be initialized with this graph and provided [name]
     */
    fun screenDestination(name: String, init: NavRoute.Builder.() -> Unit = {}): ScreenDestination =
        screenDestination(this, pathName = name, reservedNameCheck = reservedNameCheck, init = init)

    /**
     * Creates new type-safe builder for [DialogDestination]. It will be initialized with this graph and provided [name]
     */
    fun dialogDestination(pathName: String, init: NavRoute.Builder.() -> Unit = {}): DialogDestination =
        dialogDestination(this, pathName = pathName, reservedNameCheck = reservedNameCheck, init = init)

    override val graph: NavGraph
        get() = this

    override fun toString(): String {
        return "Graph($name)"
    }
}
