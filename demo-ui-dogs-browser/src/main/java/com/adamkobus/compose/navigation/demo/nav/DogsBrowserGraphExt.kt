package com.adamkobus.compose.navigation.demo.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.adamkobus.compose.navigation.demo.ui.demodialog.DemoDialog
import com.adamkobus.compose.navigation.demo.ui.dogdetails.DogDetailsScreen
import com.adamkobus.compose.navigation.demo.ui.doggallery.DogGalleryScreen
import com.adamkobus.compose.navigation.demo.ui.dogslist.DogsListScreen
import com.adamkobus.compose.navigation.demo.ui.nav.DogsBrowserGraph
import com.adamkobus.compose.navigation.demo.ui.nav.dogId
import com.adamkobus.compose.navigation.ext.composableDestination
import com.adamkobus.compose.navigation.ext.composableDialog
import com.adamkobus.compose.navigation.ext.composableNavigation

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.dogsBrowserGraph() {
    composableNavigation(DogsBrowserGraph) {
        composableDestination(DogsBrowserGraph.DogsList) { DogsListScreen() }
        composableDestination(DogsBrowserGraph.DogDetails) { entry -> DogDetailsScreen(entry.dogId()) }
        composableDialog(DogsBrowserGraph.DemoDialog) { DemoDialog() }
        composableDestination(DogsBrowserGraph.DogGallery) { entry -> DogGalleryScreen(entry.dogId()) }
    }
}
