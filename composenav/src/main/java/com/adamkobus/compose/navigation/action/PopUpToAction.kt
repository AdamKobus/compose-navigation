package com.adamkobus.compose.navigation.action

import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.destination.INavDestination

/**
 * This action results in [NavHostController.popBackStack] being called with additional options
 *
 * @param toDestination Destination to which the back stack will be popped
 * @param inclusive if true then [toDestination] will be removed from back stack as well.
 * @param saveState if true then popped destinations' state will be saved and it will be possible to restore it later
 * by navigating to any of them with [NavOptions.restoreState] set to true
 */
class PopUpToAction(
    fromDestination: INavDestination,
    toDestination: INavDestination,
    private val inclusive: Boolean = false,
    private val saveState: Boolean = false,
) : NavAction(fromDestination, toDestination) {
    /**
     * Calls [NavHostController.popBackStack]
     *
     * @return true if back stack was modified as a result of this action
     */
    override fun navigate(controller: NavHostController): Boolean {
        return controller.popBackStack(route = toDestination.route.buildRoute(), inclusive = inclusive, saveState = saveState)
    }

    /**
     * Returns a formatted String representation of [PopAction]
     */
    override fun toString(): String {
        return "PopUpToAction ${fromDestination.route.buildRoute()} -> ${toDestination.route.buildRoute()}"
    }
}
