package com.adamkobus.compose.navigation.tutorial.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.destination.NavStackEntry
import com.adamkobus.compose.navigation.destination.getInt
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableDialog
import com.adamkobus.compose.navigation.ext.composableNavigation
import com.adamkobus.compose.navigation.tutorial.ui.detail.DetailScreen
import com.adamkobus.compose.navigation.tutorial.ui.detail.DetailScreenDialog
import com.adamkobus.compose.navigation.tutorial.ui.image.ImageScreen
import com.adamkobus.compose.navigation.tutorial.ui.list.ListScreen
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

    val DetailDialog = Detail.next {
        path("dialog")
    }.asDialog()
}

fun NavStackEntry?.getItemId() = getInt(TutorialGraph.PARAM_ITEM_ID)

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
        composableDestination(TutorialGraph.Detail) { navStackEntry ->
            DetailScreen(itemId = navStackEntry.getItemId())
        }
        composableDialog(TutorialGraph.DetailDialog) { navStackEntry ->
            DetailScreenDialog(itemId = navStackEntry.getItemId())
        }
    }
}
