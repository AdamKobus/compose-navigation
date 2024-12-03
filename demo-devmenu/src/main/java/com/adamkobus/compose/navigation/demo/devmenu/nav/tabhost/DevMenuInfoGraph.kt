package com.adamkobus.compose.navigation.demo.devmenu.nav.tabhost

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.demo.devmenu.ui.info.home.InfoHome
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableNavigation

internal object DevMenuInfoGraph : NavGraph("devMenuInfo") {
    override fun startDestination() = Home

    val Home = screenDestination("home")
}

@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.devMenuInfoGraph() {
    composableNavigation(DevMenuInfoGraph) {
        composableDestination(DevMenuInfoGraph.Home) { InfoHome() }
    }
}
