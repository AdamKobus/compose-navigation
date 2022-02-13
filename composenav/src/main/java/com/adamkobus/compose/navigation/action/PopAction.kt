package com.adamkobus.compose.navigation.action

import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.destination.GlobalGraph
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.PopDestination

/**
 * This action results in [NavHostController.popBackStack] being called
 */
class PopAction(
    fromDestination: NavDestination,
    toDestination: PopDestination = GlobalGraph.Back
) : NavAction(fromDestination = fromDestination, toDestination = toDestination) {

    /**
     * Calls [NavHostController.popBackStack]
     */
    override fun navigate(controller: NavHostController) {
        controller.popBackStack()
    }

    /**
     * Returns a formatted String representation of [PopAction]
     */
    override fun toString(): String {
        return "PopAction ${fromDestination.route.buildRoute()} -> ${toDestination.route.buildRoute()}"
    }
}
