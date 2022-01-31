package com.adamkobus.compose.navigation.demo.ui.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.nav.DemoNavHost
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBar
import com.adamkobus.compose.navigation.ui.NavComposable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DemoAppRoot() {
    val navHostController = rememberAnimatedNavController()
    val vm = hiltViewModel<DemoAppRootVM>()
    NavComposable(navController = navHostController)
    Scaffold(
//        bottomBar = { AnimatedBottomBar(bottomBarState = vm.bottomBarState.value) }
    ) {
        DemoNavHost(navController = navHostController)
        AnimatedAppBar(appBarState = vm.appBarState.value)
    }
}
