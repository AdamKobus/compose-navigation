package com.adamkobus.compose.navigation

import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.data.NavAction

/**
 * Implementations of this interface should process [NavAction] by navigating to a route using [NavHostController].
 *
 * Implementations should be provided via [dagger.multibindings.IntoSet]
 */
interface NavActionProcessor {

    /**
     * Should navigate using [navController] is [action] is recognized by this processor.
     *
     * @return true if [action] was processed, false otherwise.
     */
    fun process(action: NavAction, navController: NavHostController): Boolean
}
