package com.adamkobus.compose.navigation.demo.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.demo.ui.catdetails.CatDetailsScreen
import com.adamkobus.compose.navigation.demo.ui.catslist.CatsListScreen
import com.adamkobus.compose.navigation.demo.ui.demodialog.DemoDialog
import com.adamkobus.compose.navigation.demo.ui.dogdetails.DogDetailsScreen
import com.adamkobus.compose.navigation.demo.ui.dogslist.DogsListScreen
import com.adamkobus.compose.navigation.demo.ui.splash.SplashScreen
import com.adamkobus.compose.navigation.demo.ui.welcome.WelcomeScreen
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableDialog
import com.adamkobus.compose.navigation.ext.composableNavigation
import com.adamkobus.compose.navigation.ext.getInt

object AppGraph : NavGraph {
    override val name = "appGraph"

    const val PARAM_CAT_ID = "catId"
    const val PARAM_DOG_ID = "dogId"

    val SplashScreen = navDestination("splashScreen")

    val WelcomeScreen = navDestination("welcomeScreen")

    val CatsList = navDestination("catsList")
    val CatDetails = CatsList.next {
        param(PARAM_CAT_ID)
    }

    val DogsList = navDestination("dogsList")
    val DogDetails = DogsList.next {
        param(PARAM_DOG_ID)
    }
    val DemoDialog = navDestination("demoDialog")
}

fun NavBackStackEntry.catId() = getInt(AppGraph.PARAM_CAT_ID)
fun NavBackStackEntry.dogId() = getInt(AppGraph.PARAM_DOG_ID)

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.appGraph() {
    composableNavigation(
        graph = AppGraph,
        startDestination = AppGraph.SplashScreen
    ) {

        composableDestination(AppGraph.SplashScreen) {
            SplashScreen()
        }

        composableDestination(AppGraph.WelcomeScreen) {
            WelcomeScreen()
        }

        composableDestination(AppGraph.CatsList) {
            CatsListScreen()
        }

        composableDestination(AppGraph.CatDetails) { entry ->
            CatDetailsScreen(entry.catId())
        }

        composableDestination(AppGraph.DogsList) {
            DogsListScreen()
        }

        composableDestination(AppGraph.DogDetails) { entry ->
            DogDetailsScreen(entry.dogId())
        }

        composableDialog(AppGraph.DemoDialog) {
            DemoDialog()
        }
    }
}
