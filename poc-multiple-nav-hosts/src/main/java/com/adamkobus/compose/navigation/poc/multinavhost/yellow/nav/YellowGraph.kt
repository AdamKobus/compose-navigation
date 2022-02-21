package com.adamkobus.compose.navigation.poc.multinavhost.yellow.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableDialog
import com.adamkobus.compose.navigation.ext.composableNavigation
import com.adamkobus.compose.navigation.poc.multinavhost.yellow.ui.dialog.YellowDialog
import com.adamkobus.compose.navigation.poc.multinavhost.yellow.ui.home.YellowHomeScreen
import com.adamkobus.compose.navigation.poc.multinavhost.yellow.ui.next.YellowNextScreen

object YellowGraph : NavGraph("yellowGraph") {

    override fun startDestination(): NavDestination = Home

    val Home = screenDestination("home")
    val Next = screenDestination("next")
    val Dialog = dialogDestination("dialog")
}

@ExperimentalAnimationApi
fun NavGraphBuilder.yellowGraph() {
    composableNavigation(YellowGraph) {
        composableDestination(YellowGraph.Home) { YellowHomeScreen() }
        composableDestination(YellowGraph.Next) { YellowNextScreen() }
        composableDialog(YellowGraph.Dialog) { YellowDialog() }
    }
}
