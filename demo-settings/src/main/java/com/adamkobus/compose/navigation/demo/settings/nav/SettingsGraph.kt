package com.adamkobus.compose.navigation.demo.settings.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.demo.settings.ui.home.SettingsHomeScreen
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableNavigation

object SettingsGraph : NavGraph("settingsGraph") {

    override fun startDestination(): NavDestination = SettingsHome

    val SettingsHome = navDestination("home")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settingsGraph() {
    composableNavigation(SettingsGraph) {

        composableDestination(SettingsGraph.SettingsHome) {
            SettingsHomeScreen()
        }
    }
}
