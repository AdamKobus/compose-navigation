package com.adamkobus.compose.navigation.demo.nav

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.data.NavDestination
import com.adamkobus.compose.navigation.demo.settings.nav.SettingsGraph
import com.adamkobus.compose.navigation.ext.getInt

object Destinations {

    const val PARAM_CAT_ID = "catId"
    const val PARAM_DOG_ID = "dogId"

    val SplashScreen = NavDestination(AppGraph, "splashScreen")

    val WelcomeScreen = NavDestination(AppGraph, "welcomeScreen")

    val CatsList = NavDestination(AppGraph, "catsList")
    val CatDetails = CatsList.next {
        param(PARAM_CAT_ID)
    }

    val DogsList = NavDestination(AppGraph, "dogsList")
    val DogDetails = DogsList.next {
        param(PARAM_DOG_ID)
    }

    val Settings = NavDestination(SettingsGraph)
}

fun NavBackStackEntry.catId() = getInt(Destinations.PARAM_CAT_ID)
fun NavBackStackEntry.dogId() = getInt(Destinations.PARAM_DOG_ID)
