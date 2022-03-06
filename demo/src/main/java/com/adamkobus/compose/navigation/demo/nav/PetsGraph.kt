package com.adamkobus.compose.navigation.demo.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.petsGraph() {
    catsBrowserGraph()
    dogsBrowserGraph()
}
