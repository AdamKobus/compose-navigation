package com.adamkobus.compose.navigation.demo.devmenu.ui.root

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.adamkobus.compose.navigation.ComposeNavHost
import com.adamkobus.compose.navigation.demo.devmenu.nav.DevMenuGraph
import com.adamkobus.compose.navigation.demo.devmenu.nav.DevMenuNavId
import com.adamkobus.compose.navigation.demo.devmenu.nav.devMenuGraph
import com.adamkobus.compose.navigation.demo.devmenu.theme.DevMenuTheme
import com.adamkobus.compose.navigation.demo.devmenu.ui.tabhost.DevMenuTabHost
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground
import com.adamkobus.compose.navigation.demo.ui.LifecycleAwareComponent

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun DevMenuRoot() {
    val vm: DevMenuRootVM = hiltViewModel()
    LifecycleAwareComponent(observer = vm)
    val controller = rememberNavController()
    DevMenuRootContent(controller = controller)
    BackHandler(onBack = vm.interactions.onBackPressed)
}

@ExperimentalAnimationApi
@Composable
private fun DevMenuRootContent(controller: NavHostController) {
    DevMenuTheme {
        DemoAppBackground {
            ComposeNavHost(
                startGraph = DevMenuGraph,
                controller = controller,
                navigationId = DevMenuNavId,
            ) {
                devMenuGraph()
            }
            DevMenuTabHost(
                modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
            )
        }
    }
}
