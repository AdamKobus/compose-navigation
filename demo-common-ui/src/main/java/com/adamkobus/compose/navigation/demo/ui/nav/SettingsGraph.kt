package com.adamkobus.compose.navigation.demo.ui.nav

import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.destination.NavDestination

object SettingsGraph : NavGraph("settingsGraph") {
    override fun startDestination(): NavDestination = SettingsHome

    val SettingsHome = navDestination("home")

    val Back = popDestination()
}
