package com.adamkobus.compose.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.model.NavGraphNamesExtractor
import com.adamkobus.compose.navigation.ui.NavComposable
import com.google.accompanist.navigation.animation.AnimatedNavHost

/***
 * Configures nested [AnimatedNavHost]
 *
 * @param startGraph This graph's [NavGraph.startDestination] will be the first navigation displayed to the user.
 * @see [AnimatedNavHost]
 */
@Suppress("LongParameterList")
@ExperimentalAnimationApi
@Composable
fun ComposeNavHost(
    startGraph: NavGraph,
    controller: NavHostController,
    navigationId: NavigationId,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition) =
        { fadeIn(animationSpec = tween(DEFAULT_ANIM_DURATION_MS)) },
    exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition) =
        { fadeOut(animationSpec = tween(DEFAULT_ANIM_DURATION_MS)) },
    popEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition) = enterTransition,
    popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition) = exitTransition,
    builder: NavGraphBuilder.() -> Unit
) {
    val graphRoutes = remember { NavGraphNamesExtractor.extractGraphNames(controller, startGraph.serializedRoute, builder) }
    NavComposable(navController = controller, navigationId = navigationId, observedGraphs = graphRoutes)
    AnimatedNavHost(
        navController = controller,
        startDestination = startGraph.serializedRoute,
        modifier = modifier,
        route = null,
        contentAlignment = contentAlignment,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        builder = builder
    )
}

/**
 * Same duration as the default duration used in AnimatedNavHost
 */
private const val DEFAULT_ANIM_DURATION_MS = 700
