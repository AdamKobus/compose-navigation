package com.adamkobus.compose.navigation.demo.ui.nav

import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.destination.NavStackEntry
import com.adamkobus.compose.navigation.destination.ScreenDestination
import com.adamkobus.compose.navigation.destination.getInt

object CatsBrowserGraph : NavGraph("cats") {
    const val PARAM_CAT_ID = "catId"

    override fun startDestination(): ScreenDestination = CatsList

    val CatsList = screenDestination("catsList")
    val CatDetails = CatsList.next {
        param(PARAM_CAT_ID)
    }
}

fun NavStackEntry?.catId() = getInt(CatsBrowserGraph.PARAM_CAT_ID)
