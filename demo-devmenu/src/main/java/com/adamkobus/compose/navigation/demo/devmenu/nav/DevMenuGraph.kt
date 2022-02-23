package com.adamkobus.compose.navigation.demo.devmenu.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.demo.devmenu.nav.tabhost.DevMenuInfoGraph
import com.adamkobus.compose.navigation.demo.devmenu.nav.tabhost.devMenuInfoGraph
import com.adamkobus.compose.navigation.demo.devmenu.nav.tabhost.devMenuSettingsGraph
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.ext.composableNavigation

internal object DevMenuGraph : NavGraph("devMenu") {
    override fun startDestination(): NavDestination = DevMenuInfoGraph
}

@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.devMenuGraph() {
    composableNavigation(DevMenuGraph) {
        devMenuInfoGraph()
        devMenuSettingsGraph()
    }
}
