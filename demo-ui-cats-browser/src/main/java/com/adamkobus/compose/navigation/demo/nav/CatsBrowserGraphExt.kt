package com.adamkobus.compose.navigation.demo.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.demo.ui.catdetails.CatDetailsScreen
import com.adamkobus.compose.navigation.demo.ui.catslist.CatsListScreen
import com.adamkobus.compose.navigation.demo.ui.nav.CatsBrowserGraph
import com.adamkobus.compose.navigation.demo.ui.nav.catId
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableNavigation

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.catsBrowserGraph() {
    composableNavigation(CatsBrowserGraph) {
        composableDestination(CatsBrowserGraph.CatsList) {
            CatsListScreen()
        }

        composableDestination(CatsBrowserGraph.CatDetails) { entry ->
            CatDetailsScreen(entry.catId())
        }
    }
}
