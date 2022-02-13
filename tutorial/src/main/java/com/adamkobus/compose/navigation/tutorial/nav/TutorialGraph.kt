package com.adamkobus.compose.navigation.tutorial.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableDialog
import com.adamkobus.compose.navigation.ext.composableNavigation
import com.adamkobus.compose.navigation.ext.getInt
import com.adamkobus.compose.navigation.tutorial.ui.detailscreen.DetailScreen
import com.adamkobus.compose.navigation.tutorial.ui.detailscreen.DetailScreenDialog
import com.adamkobus.compose.navigation.tutorial.ui.imagescreen.ImageScreen
import com.adamkobus.compose.navigation.tutorial.ui.listscreen.ListScreen
import com.adamkobus.compose.navigation.tutorial.ui.welcome.WelcomeScreen

object TutorialGraph : NavGraph("tutorialGraph") {
    const val PARAM_ITEM_ID = "itemId"

    override fun startDestination() = Welcome

    val Welcome = screenDestination("welcome")
    val Image = screenDestination("image")
    val List = screenDestination("list")

    val Detail = List.next {
        param(PARAM_ITEM_ID)
    }

    val Back = popDestination()

    val DetailDialog = Detail.next {
        path("dialog")
    }.asDialog()
}

fun NavBackStackEntry.getItemId() = getInt(TutorialGraph.PARAM_ITEM_ID)

@ExperimentalAnimationApi
fun NavGraphBuilder.tutorialGraph() {
    composableNavigation(TutorialGraph) {
        composableDestination(TutorialGraph.Welcome) {
            WelcomeScreen()
        }
        composableDestination(TutorialGraph.Image) {
            ImageScreen()
        }
        composableDestination(TutorialGraph.List) {
            ListScreen()
        }
        composableDestination(TutorialGraph.Detail) { navBackStackEntry ->
            DetailScreen(itemId = navBackStackEntry.getItemId())
        }
        composableDialog(TutorialGraph.DetailDialog) { navBackStackEntry ->
            DetailScreenDialog(itemId = navBackStackEntry.getItemId())
        }
    }
}
