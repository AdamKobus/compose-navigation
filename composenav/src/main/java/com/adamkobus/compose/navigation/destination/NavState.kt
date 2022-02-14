package com.adamkobus.compose.navigation.destination

/**
 * Represents the current state of the back stack
 *
 * @param currentDestination - the destination that is currently displayed to the user.
 * @param backStack - all of the destinations that are currently in the back stack, including graphs.
 */
data class NavState(
    val currentDestination: NavStackEntry?,
    val backStack: List<NavStackEntry>
) {
    /**
     * Checks if provided destination is currently in back stack
     *
     * @return true if destination was found in back stack
     */
    fun isInBackStack(dest: NavDestination): Boolean {
        return backStack.any { it.destination == dest }
    }

    /**
     * Checks if [destination]
     */
    fun isCurrent(destination: INavDestination?): Boolean {
        return currentDestination?.destination == destination
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
)

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
