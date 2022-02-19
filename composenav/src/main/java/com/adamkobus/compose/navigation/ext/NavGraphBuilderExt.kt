package com.adamkobus.compose.navigation.ext

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.destination.DialogDestination
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.destination.NavStackEntry
import com.adamkobus.compose.navigation.destination.ScreenDestination
import com.adamkobus.compose.navigation.model.toNavStackEntry
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

/**
 * @see [navigation]
 */
@Suppress("LongParameterList")
@ExperimentalAnimationApi
fun NavGraphBuilder.composableNavigation(
    graph: NavGraph,
    enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    popEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    builder: NavGraphBuilder.() -> Unit
) {
    ComposeNavigation.getNavDestinationManager().addToKnownDestinations(graph)

    navigation(
        route = graph.serializedRoute,
        startDestination = graph.startDestination().route.buildRoute(),
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        builder = builder
    )
}

/**
 * @see [composable]
 */
@Suppress("LongParameterList")
@ExperimentalAnimationApi
fun NavGraphBuilder.composableDestination(
    destination: ScreenDestination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    popEnterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    popExitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    content: @Composable AnimatedVisibilityScope.(NavStackEntry?) -> Unit
) {
    ComposeNavigation.getNavDestinationManager().addToKnownDestinations(destination)

    composable(
        destination.route.buildRoute(),
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = { backStackEntry ->
            content(backStackEntry.toNavStackEntry())
        }
    )
}

/**
 * @see dialog
 */
fun NavGraphBuilder.composableDialog(
    destination: DialogDestination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    dialogProperties: DialogProperties = DialogProperties(),
    content: @Composable (NavStackEntry?) -> Unit
) {
    dialog(
        route = destination.route.buildRoute(),
        arguments = arguments,
        deepLinks = deepLinks,
        dialogProperties = dialogProperties,
        content = { backStackEntry ->
            content(backStackEntry.toNavStackEntry())
        }
    )
}
