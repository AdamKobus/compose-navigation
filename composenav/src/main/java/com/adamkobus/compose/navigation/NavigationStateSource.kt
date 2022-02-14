package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.destination.NavState
import kotlinx.coroutines.flow.StateFlow

/**
 * Allows you to check the current state of back stack with [navState]
 *
 * Allows you to observe back stack changes with [observeNavState]
 */
interface NavigationStateSource {
    /**
     * Returns current back stack state synchronously
     */
    val navState: NavState

    /**
     * Allows you to observe the changes to the back stack
     */
    fun observeNavState(): StateFlow<NavState>
}
