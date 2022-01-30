package com.adamkobus.compose.navigation.demo.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.demo.settings.nav.settingsGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DemoNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AppGraph.name,
        modifier = modifier
    ) {
        appGraph()
        settingsGraph()
    }
}
