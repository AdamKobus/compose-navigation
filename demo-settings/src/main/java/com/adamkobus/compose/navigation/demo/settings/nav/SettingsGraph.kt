package com.adamkobus.compose.navigation.demo.settings.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.demo.settings.ui.home.SettingsHomeScreen
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableNavigation

object SettingsGraph : NavGraph {
    override val name: String = "settingsGraph"

    val SettingsHome = navDestination("home")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settingsGraph() {
    composableNavigation(
        graph = SettingsGraph,
        startDestination = SettingsGraph.SettingsHome
    ) {

        composableDestination(SettingsGraph.SettingsHome) {
            SettingsHomeScreen()
        }
    }
}
