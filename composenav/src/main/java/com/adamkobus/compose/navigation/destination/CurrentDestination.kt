package com.adamkobus.compose.navigation.destination

data class CurrentDestination(
    val destination: NavDestination?,
    val backStack: List<NavDestination>
) {
    fun isInBackStack(dest: NavDestination): Boolean {
        return backStack.contains(dest)
    }
}
