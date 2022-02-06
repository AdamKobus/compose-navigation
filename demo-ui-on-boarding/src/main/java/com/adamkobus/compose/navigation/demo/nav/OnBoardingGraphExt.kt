package com.adamkobus.compose.navigation.demo.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.demo.ui.nav.OnBoardingGraph
import com.adamkobus.compose.navigation.demo.ui.welcome.WelcomeScreen
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableNavigation

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.onBoardingGraph() {
    composableNavigation(OnBoardingGraph) {

        composableDestination(OnBoardingGraph.WelcomeScreen) {
            WelcomeScreen()
        }
    }
}
