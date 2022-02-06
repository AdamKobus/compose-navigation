package com.adamkobus.compose.navigation.demo.ui.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.nav.DemoNavHost
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBar

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DemoAppRoot() {
    val vm = hiltViewModel<DemoAppRootVM>()
    Box(modifier = Modifier.fillMaxSize()) {
        DemoNavHost()
        AnimatedAppBar(appBarState = vm.appBarState.value)
    }
}
