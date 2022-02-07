package com.adamkobus.compose.navigation.action

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.adamkobus.compose.navigation.destination.INavDestination

class NavigateAction(
    fromDestination: INavDestination,
    toDestination: INavDestination,
    private val params: List<String> = emptyList(),
    private val navigateWithController: ((NavHostController) -> Unit)? = null,
    private val navBuilder: (NavOptionsBuilder.() -> Unit)? = null
) : NavAction(fromDestination = fromDestination, toDestination = toDestination) {

    constructor(navAction: NavigateAction) : this(
        navAction.fromDestination,
        navAction.toDestination,
        navAction.params,
        navAction.navigateWithController,
        navAction.navBuilder
    )

    operator fun plus(params: List<String>): NavigateAction =
        NavigateAction(
            fromDestination,
            toDestination,
            params = this.params + params,
            navigateWithController = navigateWithController,
            navBuilder = navBuilder
        )

    operator fun plus(param: Int): NavigateAction = plus(param.toString())

    operator fun plus(param: String): NavigateAction =
        NavigateAction(
            fromDestination,
            toDestination,
            params = this.params + param,
            navigateWithController = navigateWithController,
            navBuilder = navBuilder
        )

    infix fun arg(param: Int): NavigateAction = plus(param)

    infix fun arg(param: String): NavigateAction = plus(param)

    infix fun navigate(param: NavOptionsBuilder.() -> Unit): NavigateAction =
        NavigateAction(fromDestination, toDestination, params, navBuilder = param, navigateWithController = navigateWithController)

    infix fun navigateWithController(param: (NavHostController) -> Unit): NavigateAction =
        NavigateAction(fromDestination, toDestination, params, navBuilder = navBuilder, navigateWithController = param)

    override fun navigate(controller: NavHostController) {
        navigateWithController?.let { controllerAction ->
            controllerAction(controller)
        } ?: navBuilder?.let { builder ->
            controller.navigate(toDestination.route.buildPath(params), builder)
        } ?: controller.navigate(toDestination.route.buildPath(params))
    }

    override fun equals(other: Any?): Boolean {
        return other is NavigateAction &&
            other.fromDestination == fromDestination &&
            other.toDestination == toDestination &&
            other.params == params &&
            other.navBuilder == navBuilder &&
            other.navigateWithController == navigateWithController
    }

    override fun hashCode(): Int {
        var result = fromDestination.hashCode()
        result = 31 * result + toDestination.hashCode()
        result = 31 * result + params.hashCode()
        result = 31 * result + (navBuilder?.hashCode() ?: 0)
        result = 31 * result + (navigateWithController?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "${super.toString()} [${params.joinToString(", ")}]"
    }
}
