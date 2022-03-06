package com.adamkobus.compose.navigation.demo.ui.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.ComposeNavHost
import com.adamkobus.compose.navigation.demo.nav.appGraph
import com.adamkobus.compose.navigation.demo.nav.onBoardingGraph
import com.adamkobus.compose.navigation.demo.nav.petsGraph
import com.adamkobus.compose.navigation.demo.settings.nav.settingsGraph
import com.adamkobus.compose.navigation.demo.ui.nav.AppRootGraph
import com.adamkobus.compose.navigation.demo.ui.nav.DemoNavigationId
import com.adamkobus.compose.navigation.demo.ui.overlay.AppOverlays
import com.adamkobus.compose.navigation.ext.composableNavigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DemoAppRoot() {
    val vm = hiltViewModel<DemoAppRootVM>()
    val controller = rememberAnimatedNavController()
    Box(modifier = Modifier.fillMaxSize()) {
        ComposeNavHost(
            startGraph = AppRootGraph,
            controller = controller,
            navigationId = DemoNavigationId,
            modifier = Modifier.fillMaxSize()
        ) {
            composableNavigation(AppRootGraph) {
                appGraph()
                onBoardingGraph()
                petsGraph()
                settingsGraph()
                vm.applyNavGraphsTask.apply(this)
            }
        }
        AppOverlays(vm.overlays)
    }
}

@Composable
private fun BoxScope.AppOverlays(overlays: AppOverlays) {
    overlays.providers.forEach {
        it.provideOverlay()(this)
    }
}
