package com.adamkobus.compose.navigation.poc.multinavhost.yellow.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.ComposeNavHost
import com.adamkobus.compose.navigation.NavigationId
import com.adamkobus.compose.navigation.poc.multinavhost.ui.theme.YellowTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun YellowNavHost(controller: NavHostController, modifier: Modifier = Modifier) {
    YellowTheme {
        ComposeNavHost(
            startGraph = YellowGraph,
            controller = controller,
            navigationId = YellowNavId,
            modifier = modifier
        ) {
            yellowGraph()
        }
    }
}

val YellowNavId = NavigationId("yellow")
