package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.NavigationId

/**
 * Represents the current state of the back stack in all of the tracked controllers.
 *
 * @param controllersState Each [NavControllerState] represents the back stack state of tracked NavHost.
 */
data class NavState(
    val controllersState: List<NavControllerState>
) {
    /**
     * Checks if provided destination is currently in back stack of any of the tracked controllers
     *
     * @return true if destination was found in back stack
     */
    fun isInBackStack(dest: INavDestination): Boolean {
        return controllersState.any { it.isInBackStack(dest) }
    }

    /**
     * Checks if [destination] is currently visible to the user in any of the tracked controllers.
     */
    fun isCurrent(destination: INavDestination?): Boolean {
        return controllersState.any { it.isCurrent(destination) }
    }

    /**
     * Searches for controller that has [dest] in its backstack
     */
    fun find(dest: INavDestination): NavControllerState? {
        return controllersState.find { it.isInBackStack(dest) }
    }

    /**
     * Returns a controller with provided [id] or null of no such controller can be found
     */
    fun get(id: NavigationId): NavControllerState? {
        return controllersState.find { it.navId == id }
    }
}

/**
 * Represents the current state of the back stack in controller identified by [navId]
 *
 * @param navId [NavigationId] of controller that is responsible for handling represented back stack.
 * @param currentDestination the destination that is currently displayed to the user.
 * @param backStack all of the destinations that are currently in the back stack, including graphs.
 */
data class NavControllerState(
    val navId: NavigationId,
    val currentDestination: NavStackEntry?,
    val backStack: List<NavStackEntry>
) {
    /**
     * Checks if provided destination is currently in back stack
     *
     * @return true if destination was found in back stack
     */
    fun isInBackStack(dest: INavDestination): Boolean {
        return backStack.any { it.destination == dest }
    }

    /**
     * Checks if [destination] is currently visible to the user
     */
    fun isCurrent(destination: INavDestination?): Boolean {
        return currentDestination?.destination == destination
    }

    /**
     * Returns a String representation of [NavControllerState]
     */
    override fun toString(): String {
        return "$currentDestination | ${backStack.joinToString(separator = " > ")}"
    }
}

/**
 * This class holds information about single entry in navigation back stack.
 *
 * If destination has any arguments in its route, then it's guaranteed that their values will be present in [arguments].
 * That's because Compose Navigation library does not support optional arguments by design.
 * Assumption is that any param that has been declared in the route always receive value.
 * It being optional should be indicated in the serialized value's string itself,
 * i.e. by serializing the value to strings like "missing" and "present(42)"
 *
 * @param destination destination linked to this back stack entry
 * @param arguments args with which this destination was launched
 */
data class NavStackEntry(
    val destination: NavDestination,
    val arguments: Map<String, String>
) {
    /**
     * Returns a String representation of [NavStackEntry]
     */
    override fun toString(): String {
        return destination.route.buildPath(arguments.values.toList())
    }
}

/**
 * @param key Name by which this param can be identified in destination's route
 * @return String param embedded into this destination's route
 *
 * @throws NullPointerException if stack entry or arguments are null or argument with provided key does not exist
 */
fun NavStackEntry?.getString(key: String): String =
    this?.arguments?.get(key)!!

/**
 * @return Int embedded into this destination's route
 *
 * @throws NullPointerException if stack entry or arguments are null or argument with provided key does not exist
 */
fun NavStackEntry?.getInt(key: String): Int =
    getString(key).toInt()

/**
 * @return Long embedded into this destination's route
 *
 * @throws NullPointerException if stack entry or arguments are null or argument with provided key does not exist
 */
fun NavStackEntry?.getLong(key: String): Long =
    getString(key).toLong()
