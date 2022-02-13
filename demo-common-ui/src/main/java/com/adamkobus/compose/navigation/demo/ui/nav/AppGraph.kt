package com.adamkobus.compose.navigation.demo.ui.nav

import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.destination.ScreenDestination

object AppGraph : NavGraph("appGraph") {
    override fun startDestination(): ScreenDestination = SplashScreen

    val SplashScreen = screenDestination("splashScreen")
}
