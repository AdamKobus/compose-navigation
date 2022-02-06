package com.adamkobus.compose.navigation.demo.ui.nav

import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.destination.NavDestination

object OnBoardingGraph : NavGraph("onBoardingGraph") {
    override fun startDestination(): NavDestination = WelcomeScreen

    val WelcomeScreen = navDestination("welcomeScreen")
}
