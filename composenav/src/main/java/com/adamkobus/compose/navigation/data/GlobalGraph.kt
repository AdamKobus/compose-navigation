package com.adamkobus.compose.navigation.data

import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.popDestination

object GlobalGraph : NavGraph("__global__") {

    private val Root = navDestination("__root__")

    override fun startDestination(): NavDestination = Root

    val Back = popDestination(this)
}
