package com.adamkobus.compose.navigation.demo.nav

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.data.navDestination
import com.adamkobus.compose.navigation.demo.settings.nav.SettingsGraph
import com.adamkobus.compose.navigation.ext.getInt

object Destinations {

    const val PARAM_CAT_ID = "catId"
    const val PARAM_DOG_ID = "dogId"

    val SplashScreen = navDestination(AppGraph, "splashScreen")

    val WelcomeScreen = navDestination(AppGraph, "welcomeScreen")

    val CatsList = navDestination(AppGraph, "catsList")
    val CatDetails = CatsList.next {
        param(PARAM_CAT_ID)
    }

    val DogsList = navDestination(AppGraph, "dogsList")
    val DogDetails = DogsList.next {
        param(PARAM_DOG_ID)
    }

    val Settings = navDestination(SettingsGraph)
}

fun NavBackStackEntry.catId() = getInt(Destinations.PARAM_CAT_ID)
fun NavBackStackEntry.dogId() = getInt(Destinations.PARAM_DOG_ID)
