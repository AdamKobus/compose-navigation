package com.adamkobus.compose.navigation.data

import com.adamkobus.compose.navigation.destination.DialogDestination
import com.adamkobus.compose.navigation.destination.INavDestination
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.NavRoute
import com.adamkobus.compose.navigation.destination.PopDestination
import com.adamkobus.compose.navigation.destination.dialogDestination
import com.adamkobus.compose.navigation.destination.navDestination
import com.adamkobus.compose.navigation.destination.navRoute
import com.adamkobus.compose.navigation.destination.popDestination
import com.adamkobus.compose.navigation.intent.NavIntent
import com.adamkobus.compose.navigation.intent.navIntent

abstract class NavGraph internal constructor(
    val name: String,
    private val reservedNameCheck: Boolean = true
) : INavDestination {

    constructor(name: String) : this(name, reservedNameCheck = true)

    override val route: NavRoute = navRoute(name, reservedNamesCheck = reservedNameCheck)

    abstract fun startDestination(): INavDestination

    fun navDestination(pathName: String, init: NavRoute.Builder.() -> Unit = {}): NavDestination =
        navDestination(this, pathName = pathName, reservedNameCheck = reservedNameCheck, init = init)

    fun dialogDestination(pathName: String, init: NavRoute.Builder.() -> Unit = {}): DialogDestination =
        dialogDestination(this, pathName = pathName, reservedNameCheck = reservedNameCheck, init = init)

    fun popDestination(): PopDestination =
        popDestination(this)

    fun navIntent(pathName: String): NavIntent =
        navIntent(this, pathName, reservedNameCheck = reservedNameCheck)

    override val graph: NavGraph
        get() = this

    override fun toString(): String {
        return "Graph($name)"
    }
}
