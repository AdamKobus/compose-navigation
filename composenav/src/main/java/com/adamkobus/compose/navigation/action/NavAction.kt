package com.adamkobus.compose.navigation.action

import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.data.GlobalGraph
import com.adamkobus.compose.navigation.data.ResolveResult
import com.adamkobus.compose.navigation.destination.INavDestination

abstract class NavAction(
    val fromDestination: INavDestination,
    val toDestination: INavDestination
) {

    override fun equals(other: Any?): Boolean {
        return other is NavAction &&
            other.fromDestination == fromDestination &&
            other.toDestination == toDestination
    }

    override fun hashCode(): Int {
        var result = fromDestination.hashCode()
        result = 31 * result + toDestination.hashCode()
        return result
    }

    fun asResult(): ResolveResult = ResolveResult.Action(this)

    override fun toString(): String {
        return "NavAction ${fromDestination.route.buildRoute()} -> ${toDestination.route.buildRoute()}"
    }

    internal abstract fun navigate(controller: NavHostController)

    companion object {
        /**
         * Use it to trigger NavHostController.popBackStack() from anywhere in your application.
         * It is advised that you create your own back actions with source destination different than Global.
         * Using Global source destination limits the usefulness of the information you receive in NavActionVerifier.
         */
        val Back = GlobalGraph goTo GlobalGraph.Back
    }
}
