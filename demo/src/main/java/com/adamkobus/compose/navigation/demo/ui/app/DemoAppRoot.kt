package com.adamkobus.compose.navigation.demo.ui.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.nav.DemoNavHost
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBar

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DemoAppRoot() {
    val vm = hiltViewModel<DemoAppRootVM>()
    Scaffold(
//        bottomBar = { AnimatedBottomBar(bottomBarState = vm.bottomBarState.value) }
    ) {
        DemoNavHost()
        AnimatedAppBar(appBarState = vm.appBarState.value)
    }
}
