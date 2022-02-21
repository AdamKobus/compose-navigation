package com.adamkobus.compose.navigation.demo.ui.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.ComposeNavHost
import com.adamkobus.compose.navigation.demo.nav.appGraph
import com.adamkobus.compose.navigation.demo.nav.onBoardingGraph
import com.adamkobus.compose.navigation.demo.nav.petsGraph
import com.adamkobus.compose.navigation.demo.settings.nav.settingsGraph
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBar
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.nav.AppGraph
import com.adamkobus.compose.navigation.demo.ui.nav.DemoNavigationId
import com.adamkobus.compose.navigation.demo.ui.tabhost.DemoTabsNavigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DemoAppRoot() {
    val vm = hiltViewModel<DemoAppRootVM>()
    val controller = rememberAnimatedNavController()
    Box(modifier = Modifier.fillMaxSize()) {
        ComposeNavHost(
            startGraph = AppGraph,
            controller = controller,
            navigationId = DemoNavigationId,
            modifier = Modifier.fillMaxSize()
        ) {
            appGraph()
            onBoardingGraph()
            petsGraph()
            settingsGraph()
        }
        AppOverlays(vm.appBarState)
    }
}

@Composable
private fun BoxScope.AppOverlays(appBarState: State<AnimatedAppBarState>) {
    AnimatedAppBar(appBarState = appBarState.value)
    DemoTabsNavigation(modifier = Modifier.align(Alignment.BottomCenter))
}
