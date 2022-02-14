package com.adamkobus.compose.navigation.action

import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.destination.NavDestination

/**
 * Represents an action that changes current destination to [toDestination]
 *
 * @param fromDestination source back stack entry that initialized the action
 * @param toDestination back stack entry that should be reached as a result of navigation
 * @param params List of arguments that will be used to build [toDestination] formatted path.
 * @param options [NavOptions] that if present, will influence the way navigation is performed.
 */
class NavigateAction(
    private val fromNavDestination: NavDestination,
    private val toNavDestination: NavDestination,
    private val params: List<String> = emptyList(),
    private val options: NavOptions? = null,
) : NavAction(fromDestination = fromNavDestination, toDestination = toNavDestination) {

    /**
     * This constructor allows you to copy other [NavigateAction]
     */
    constructor(navAction: NavigateAction) : this(
        navAction.fromNavDestination,
        navAction.toNavDestination,
        navAction.params,
        navAction.options
    )

    /**
     * Provided [param] will be delivered to [toDestination]
     */
    infix fun arg(param: Int): NavigateAction = arg(param.toString())

    /**
     * Provided [param] will be delivered to [toDestination]
     */
    infix fun arg(param: String): NavigateAction =
        NavigateAction(
            fromNavDestination,
            toNavDestination,
            params = this.params + param,
            options = options
        )

    /**
     * Sets the options with which navigation action should be performed. Use [navActionOptions] builder to create new set of options.
     *
     * @param param those options will be provided to [NavHostController.navigate]
     */
    infix fun withOptions(param: NavOptions?): NavigateAction =
        NavigateAction(
            fromNavDestination,
            toNavDestination,
            params,
            options = param
        )

    override fun navigate(controller: NavHostController) {
        options?.let { builder ->
            controller.navigate(toDestination.route.buildPath(params), navOptions = options.toAndroidNavOptions())
        } ?: controller.navigate(toDestination.route.buildPath(params))
    }

    /**
     * Compares with other [NavigateAction] by [fromDestination], [toDestination], [params] and [options] fields
     */
    override fun equals(other: Any?): Boolean {
        return other is NavigateAction &&
            other.fromDestination == fromDestination &&
            other.toDestination == toDestination &&
            other.params == params &&
            other.options == options
    }

    /**
     * Generates hash code based on [fromDestination], [toDestination], [params] and [options] fields
     */
    override fun hashCode(): Int {
        var result = fromDestination.hashCode()
        result = 31 * result + toDestination.hashCode()
        result = 31 * result + params.hashCode()
        result = 31 * result + (options?.hashCode() ?: 0)
        return result
    }

    /**
     * @return formatted String representation of [NavigateAction]
     */
    override fun toString(): String {
        return "${super.toString()} [${params.joinToString(", ")}]"
    }
}
