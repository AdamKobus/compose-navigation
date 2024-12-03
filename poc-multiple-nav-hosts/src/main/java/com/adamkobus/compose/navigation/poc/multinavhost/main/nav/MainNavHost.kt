package com.adamkobus.compose.navigation.poc.multinavhost.main.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.adamkobus.compose.navigation.ComposeNavHost
import com.adamkobus.compose.navigation.NavigationId

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavHost() {
    val controller = rememberNavController()
    ComposeNavHost(
        startGraph = PocGraph,
        controller = controller,
        navigationId = MainNavId,
    ) {
        pocGraph()
    }
}

val MainNavId = NavigationId("main")
