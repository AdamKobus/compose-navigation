package com.adamkobus.compose.navigation.poc.multinavhost.green.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableDialog
import com.adamkobus.compose.navigation.ext.composableNavigation
import com.adamkobus.compose.navigation.poc.multinavhost.green.ui.dialog.GreenDialog
import com.adamkobus.compose.navigation.poc.multinavhost.green.ui.home.GreenHomeScreen
import com.adamkobus.compose.navigation.poc.multinavhost.green.ui.next.GreenNextScreen

object GreenGraph : NavGraph("greenGraph") {

    override fun startDestination(): NavDestination = Home

    val Home = screenDestination("home")
    val Next = screenDestination("next")
    val Dialog = dialogDestination("dialog")
}

@ExperimentalAnimationApi
fun NavGraphBuilder.greenGraph() {
    composableNavigation(GreenGraph) {
        composableDestination(GreenGraph.Home) { GreenHomeScreen() }
        composableDestination(GreenGraph.Next) { GreenNextScreen() }
        composableDialog(GreenGraph.Dialog) { GreenDialog() }
    }
}
