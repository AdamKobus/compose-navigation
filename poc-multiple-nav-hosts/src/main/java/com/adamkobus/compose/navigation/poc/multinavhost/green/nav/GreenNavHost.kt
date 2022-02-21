package com.adamkobus.compose.navigation.poc.multinavhost.green.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.ComposeNavHost
import com.adamkobus.compose.navigation.NavigationId
import com.adamkobus.compose.navigation.poc.multinavhost.ui.theme.GreenTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GreenNavHost(controller: NavHostController, modifier: Modifier = Modifier) {
    GreenTheme {
        ComposeNavHost(
            startGraph = GreenGraph,
            controller = controller,
            navigationId = GreenNavId,
            modifier = modifier
        ) {
            greenGraph()
        }
    }
}

val GreenNavId = NavigationId("green")
