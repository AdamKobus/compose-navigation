package com.adamkobus.compose.navigation.poc.multinavhost.main.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.adamkobus.compose.navigation.ComposeNavHost
import com.adamkobus.compose.navigation.NavigationId
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavHost() {
    val controller = rememberAnimatedNavController()
    ComposeNavHost(
        startGraph = PocGraph,
        controller = controller,
        navigationId = MainNavId
    ) {
        pocGraph()
    }
}

val MainNavId = NavigationId("main")
