package com.adamkobus.compose.navigation.data

import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.popDestination

object GlobalGraph : NavGraph(name = "__global__", reservedNameCheck = false) {

    private val Root = navDestination("__root__")

    override fun startDestination(): NavDestination = Root

    val Back = popDestination(this)
}
