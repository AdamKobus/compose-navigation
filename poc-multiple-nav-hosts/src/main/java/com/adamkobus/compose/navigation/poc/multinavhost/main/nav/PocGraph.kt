package com.adamkobus.compose.navigation.poc.multinavhost.main.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableNavigation
import com.adamkobus.compose.navigation.poc.multinavhost.main.ui.demo.DemoScreen
import com.adamkobus.compose.navigation.poc.multinavhost.main.ui.home.HomeScreen

object PocGraph : NavGraph("pocGraph") {
    override fun startDestination(): NavDestination = Home

    val Home = screenDestination("home")
    val Demo = screenDestination("demo")
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.pocGraph() {
    composableNavigation(PocGraph) {
        composableDestination(PocGraph.Home) { HomeScreen() }
        composableDestination(PocGraph.Demo) { DemoScreen() }
    }
}
