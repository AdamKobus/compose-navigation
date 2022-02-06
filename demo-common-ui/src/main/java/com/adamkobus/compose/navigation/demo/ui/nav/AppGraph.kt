package com.adamkobus.compose.navigation.demo.ui.nav

import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.destination.NavDestination

object AppGraph : NavGraph("appGraph") {
    override fun startDestination(): NavDestination = SplashScreen

    val SplashScreen = navDestination("splashScreen")
}
