package com.adamkobus.compose.navigation.demo.settings.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.demo.settings.ui.home.SettingsHomeScreen
import com.adamkobus.compose.navigation.demo.ui.nav.SettingsGraph
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableNavigation

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settingsGraph() {
    composableNavigation(SettingsGraph) {
        composableDestination(SettingsGraph.SettingsHome) {
            SettingsHomeScreen()
        }
    }
}
