package com.adamkobus.compose.navigation.data

import com.adamkobus.compose.navigation.action.NavigateAction
import com.adamkobus.compose.navigation.action.PopAction
import com.adamkobus.compose.navigation.destination.INavDestination
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.NavRoute
import com.adamkobus.compose.navigation.destination.PopDestination
import com.adamkobus.compose.navigation.destination.navDestination
import com.adamkobus.compose.navigation.destination.navRoute
import com.adamkobus.compose.navigation.destination.popDestination

abstract class NavGraph internal constructor(
    val name: String,
    private val reservedNameCheck: Boolean = true
) : INavDestination {

    constructor(name: String) : this(name, reservedNameCheck = true)

    override val route: NavRoute = navRoute(name, reservedNamesCheck = reservedNameCheck)

    abstract fun startDestination(): NavDestination

    fun navDestination(pathName: String, init: NavRoute.Builder.() -> Unit = {}): NavDestination =
        navDestination(this, pathName = pathName, reservedNameCheck = reservedNameCheck, init = init)

    fun popDestination(): PopDestination =
        popDestination(this)

    override val graph: NavGraph
        get() = this

    operator fun plus(other: PopDestination): PopAction = to(other)

    infix fun to(other: PopDestination): PopAction = PopAction(this, other)

    operator fun plus(other: NavDestination): NavigateAction = to(other)

    infix fun to(other: NavDestination) = NavigateAction(this, other)

    operator fun plus(other: NavGraph): NavigateAction = to(other)

    infix fun to(other: NavGraph): NavigateAction = NavigateAction(this, other)

    override fun toString(): String {
        return "Graph($name) ${startDestination()}"
    }
}
