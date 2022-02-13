package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.destination.CurrentDestination
import kotlinx.coroutines.flow.StateFlow

/**
 * Allows you to check the current state of back stack with [currentDestination]
 *
 * Allows you to observe back stack changes with [observeCurrentDestination]
 */
interface NavigationStateSource {
    /**
     * Returns current back stack state synchronously
     */
    val currentDestination: CurrentDestination

    /**
     * Allows you to observe the changes to the back stack
     */
    fun observeCurrentDestination(): StateFlow<CurrentDestination>
}
