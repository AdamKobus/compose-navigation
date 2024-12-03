package com.adamkobus.compose.navigation.demo.devmenu.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.demo.devmenu.ui.root.DevMenuRoot
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableNavigation

internal object DevMenuRootGraph : NavGraph("devMenuRoot") {
    override fun startDestination() = Root

    val Root = screenDestination("root")
}

@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.devMenuRootGraph() {
    composableNavigation(DevMenuRootGraph) {
        composableDestination(DevMenuRootGraph.Root) { DevMenuRoot() }
    }
}
