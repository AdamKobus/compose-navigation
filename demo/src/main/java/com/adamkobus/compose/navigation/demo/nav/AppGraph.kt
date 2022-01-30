package com.adamkobus.compose.navigation.demo.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.demo.ui.catdetails.CatDetailsScreen
import com.adamkobus.compose.navigation.demo.ui.catslist.CatsListScreen
import com.adamkobus.compose.navigation.demo.ui.dogdetails.DogDetailsScreen
import com.adamkobus.compose.navigation.demo.ui.dogslist.DogsListScreen
import com.adamkobus.compose.navigation.demo.ui.splash.SplashScreen
import com.adamkobus.compose.navigation.demo.ui.welcome.WelcomeScreen
import com.adamkobus.compose.navigation.ext.composableDestination
import com.google.accompanist.navigation.animation.navigation

object AppGraph : NavGraph {
    override val name = "appGraph"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.appGraph() {
    navigation(
        route = AppGraph.name,
        startDestination = Destinations.SplashScreen.route.buildRoute()
    ) {

        composableDestination(Destinations.SplashScreen) {
            SplashScreen()
        }

        composableDestination(Destinations.WelcomeScreen) {
            WelcomeScreen()
        }

        composableDestination(Destinations.CatsList) {
            CatsListScreen()
        }

        composableDestination(Destinations.CatDetails) { entry ->
            CatDetailsScreen(entry.catId())
        }

        composableDestination(Destinations.DogsList) {
            DogsListScreen()
        }

        composableDestination(Destinations.DogDetails) { entry ->
            DogDetailsScreen(entry.dogId())
        }
    }
}
