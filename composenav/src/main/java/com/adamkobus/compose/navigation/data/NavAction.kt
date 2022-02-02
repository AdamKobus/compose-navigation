package com.adamkobus.compose.navigation.data

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

open class NavAction(
    val fromDestination: INavDestination,
    val toDestination: INavDestination,
    val params: List<String> = emptyList(),
    val navigateWithController: ((NavHostController) -> Unit)? = null,
    val navigate: (NavOptionsBuilder.() -> Unit)? = null,
) {
    constructor(navAction: NavAction) : this(
        navAction.fromDestination, navAction.toDestination, navAction.params, navAction.navigateWithController, navAction.navigate
    )

    operator fun plus(params: List<String>): NavAction =
        NavAction(
            fromDestination,
            toDestination,
            params = this.params + params,
            navigateWithController = navigateWithController,
            navigate = navigate
        )

    operator fun plus(param: Int): NavAction = plus(param.toString())

    operator fun plus(param: String): NavAction =
        NavAction(
            fromDestination,
            toDestination,
            params = this.params + param,
            navigateWithController = navigateWithController,
            navigate = navigate
        )

    infix fun arg(param: Int): NavAction = plus(param)

    infix fun arg(param: String): NavAction = plus(param)

    infix fun navigate(param: NavOptionsBuilder.() -> Unit): NavAction =
        NavAction(fromDestination, toDestination, params, navigate = param)

    override fun equals(other: Any?): Boolean {
        return other is NavAction &&
            other.fromDestination == fromDestination &&
            other.toDestination == toDestination &&
            other.params == params &&
            other.navigate == navigate &&
            other.navigateWithController == navigateWithController
    }

    override fun hashCode(): Int {
        var result = fromDestination.hashCode()
        result = 31 * result + toDestination.hashCode()
        result = 31 * result + params.hashCode()
        result = 31 * result + (navigate?.hashCode() ?: 0)
        result = 31 * result + (navigateWithController?.hashCode() ?: 0)
        return result
    }

    companion object {
        val Back = NavAction(GlobalDestination, PopBackStackDestination, navigateWithController = { it.popBackStack() })
    }
}
