package com.adamkobus.compose.navigation.demo.ui.nav

import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.NavGraph

object AppRootGraph : NavGraph("appRootGraph") {
    override fun startDestination(): NavDestination = AppGraph
}
