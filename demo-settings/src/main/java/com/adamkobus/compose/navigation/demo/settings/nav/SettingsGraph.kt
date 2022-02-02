package com.adamkobus.compose.navigation.demo.settings.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.demo.settings.ui.home.HomeScreen
import com.adamkobus.compose.navigation.ext.composableDestination

object SettingsGraph : NavGraph {
    override val name: String = "settingsGraph"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settingsGraph() {
    navigation(
        route = SettingsGraph.name,
        startDestination = Destinations.SettingsHome.route.buildRoute()
    ) {

        composableDestination(Destinations.SettingsHome) {
            HomeScreen()
        }
    }
}
