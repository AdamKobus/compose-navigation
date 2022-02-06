package com.adamkobus.compose.navigation.demo.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.demo.ui.nav.AppGraph
import com.adamkobus.compose.navigation.demo.ui.splash.SplashScreen
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableNavigation

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.appGraph() {
    composableNavigation(AppGraph) {
        composableDestination(AppGraph.SplashScreen) {
            SplashScreen()
        }
    }
}
