package com.adamkobus.compose.navigation.demo.ui.nav

import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.destination.ScreenDestination

object SettingsGraph : NavGraph("settingsGraph") {
    override fun startDestination(): ScreenDestination = SettingsHome

    val SettingsHome = screenDestination("home")

    val Back = popDestination()
}
