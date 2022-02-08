package com.adamkobus.compose.navigation.demo.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.demo.ui.nav.CatsBrowserGraph
import com.adamkobus.compose.navigation.ext.composableNavigation

object PetsGraph : NavGraph("petsGraph") {
    override fun startDestination() = CatsBrowserGraph
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.petsGraph() {
    composableNavigation(PetsGraph) {
        catsBrowserGraph()
        dogsBrowserGraph()
    }
}
