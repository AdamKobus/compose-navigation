package com.adamkobus.compose.navigation.demo.ui.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.nav.DemoNavHost
import com.adamkobus.compose.navigation.demo.ui.topbar.DemoTopBar
import com.adamkobus.compose.navigation.ui.NavComposable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DemoAppRoot() {
    val vm = hiltViewModel<DemoAppRootVM>()
    val navHostController = rememberAnimatedNavController()
    NavComposable(navController = navHostController)

    val topBarState = vm.topBarState.value

    Scaffold(
        topBar = { DemoTopBar(state = topBarState) }
    ) {
        DemoNavHost(navController = navHostController)
    }
}
