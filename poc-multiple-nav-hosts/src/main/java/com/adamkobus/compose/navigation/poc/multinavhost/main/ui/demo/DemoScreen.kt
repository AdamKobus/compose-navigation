package com.adamkobus.compose.navigation.poc.multinavhost.main.ui.demo

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.poc.multinavhost.green.nav.GreenNavHost
import com.adamkobus.compose.navigation.poc.multinavhost.ui.common.ScreenBackground
import com.adamkobus.compose.navigation.poc.multinavhost.yellow.nav.YellowNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun DemoScreen() {
    DemoScreenContent()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun DemoScreenContent() {
    ScreenBackground {
        val configuration = LocalConfiguration.current
        val greenController = rememberAnimatedNavController()
        val yellowController = rememberAnimatedNavController()
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LandscapeContent(greenController, yellowController)
            else -> PortraitContent(greenController, yellowController)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun PortraitContent(greenController: NavHostController, yellowController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        GreenScreen(
            greenController,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        YellowScreen(
            yellowController,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
private fun LandscapeContent(greenController: NavHostController, yellowController: NavHostController) {
    Row(modifier = Modifier.fillMaxSize()) {
        GreenScreen(
            greenController,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
        YellowScreen(
            yellowController,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun GreenScreen(
    controller: NavHostController,
    modifier: Modifier = Modifier
) {
    GreenNavHost(controller = controller, modifier = modifier)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun YellowScreen(
    controller: NavHostController,
    modifier: Modifier = Modifier
) {
    YellowNavHost(controller = controller, modifier = modifier)
}
