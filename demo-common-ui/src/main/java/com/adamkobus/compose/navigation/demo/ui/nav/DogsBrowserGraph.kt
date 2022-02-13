package com.adamkobus.compose.navigation.demo.ui.nav

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.destination.ScreenDestination
import com.adamkobus.compose.navigation.ext.getInt

object DogsBrowserGraph : NavGraph("dogs") {
    const val PARAM_DOG_ID = "dogId"

    override fun startDestination(): ScreenDestination = DogsList

    val DogsList = screenDestination("dogsList")
    val DogDetails = DogsList.next {
        param(PARAM_DOG_ID)
    }
    val DemoDialog = dialogDestination("demoDialog")
    val Back = popDestination()
}

fun NavBackStackEntry.dogId() = getInt(DogsBrowserGraph.PARAM_DOG_ID)
