package com.adamkobus.compose.navigation.demo.ui.nav

import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.destination.ScreenDestination

object OnBoardingGraph : NavGraph("onBoardingGraph") {
    override fun startDestination(): ScreenDestination = WelcomeScreen

    val WelcomeScreen = screenDestination("welcomeScreen")
}
