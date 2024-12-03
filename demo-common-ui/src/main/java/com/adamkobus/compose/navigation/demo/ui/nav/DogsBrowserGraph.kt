package com.adamkobus.compose.navigation.demo.ui.nav

import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.destination.NavStackEntry
import com.adamkobus.compose.navigation.destination.ScreenDestination

object DogsBrowserGraph : NavGraph("dogs") {
    const val PARAM_DOG_ID = "dogId"

    override fun startDestination(): ScreenDestination = DogsList

    val DogsList = screenDestination("dogsList")
    val DogDetails =
        DogsList.next {
            param(PARAM_DOG_ID)
        }
    val DemoDialog = dialogDestination("demoDialog")
    val DogGallery =
        DogDetails.next {
            path("gallery")
        }
}

fun NavStackEntry.dogId() = getInt(DogsBrowserGraph.PARAM_DOG_ID)
