package com.adamkobus.compose.navigation.demo.ui.nav

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.destination.NavDestination
import com.adamkobus.compose.navigation.ext.getInt

object CatsBrowserGraph : NavGraph("cats") {
    const val PARAM_CAT_ID = "catId"

    override fun startDestination(): NavDestination = CatsList

    val CatsList = navDestination("catsList")
    val CatDetails = CatsList.next {
        param(PARAM_CAT_ID)
    }

    val Back = popDestination()
}

fun NavBackStackEntry.catId() = getInt(CatsBrowserGraph.PARAM_CAT_ID)
