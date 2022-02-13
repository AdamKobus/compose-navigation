package com.adamkobus.compose.navigation.action

import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.destination.INavDestination
import com.adamkobus.compose.navigation.intent.ResolveResult

/**
 * Base class for all navigation actions
 * @param fromDestination source of the action
 * @param toDestination target of the action
 */
abstract class NavAction(
    val fromDestination: INavDestination,
    val toDestination: INavDestination
) {

    /**
     * Compares other [NavAction] by [fromDestination] and [toDestination] fields
     */
    override fun equals(other: Any?): Boolean {
        return other is NavAction &&
            other.fromDestination == fromDestination &&
            other.toDestination == toDestination
    }

    /**
     * Generates hash code based on [fromDestination] and [toDestination] fields
     */
    override fun hashCode(): Int {
        var result = fromDestination.hashCode()
        result = 31 * result + toDestination.hashCode()
        return result
    }

    /**
     * Converts the action into [ResolveResult.Action]
     */
    fun asResult(): ResolveResult = ResolveResult.Action(this)

    /**
     * Returns String representation of [NavAction]
     */
    override fun toString(): String {
        return "NavAction ${fromDestination.route.buildRoute()} -> ${toDestination.route.buildRoute()}"
    }

    /**
     * This method is used to perform actual navigation
     */
    internal abstract fun navigate(controller: NavHostController)
}
