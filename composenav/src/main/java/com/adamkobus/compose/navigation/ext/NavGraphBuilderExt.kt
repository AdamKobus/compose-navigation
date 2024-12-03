package com.adamkobus.compose.navigation.ext

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navigation
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.destination.DialogDestination
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.destination.NavStackEntry
import com.adamkobus.compose.navigation.destination.ScreenDestination
import com.adamkobus.compose.navigation.model.toNavStackEntry
import com.adamkobus.compose.navigation.ui.LocalNavDestination

/**
 * @see [navigation]
 */
@Suppress("LongParameterList")
@ExperimentalAnimationApi
fun NavGraphBuilder.composableNavigation(
    graph: NavGraph,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    builder: NavGraphBuilder.() -> Unit,
) {
    ComposeNavigation.getKnownDestinationsSource().addToKnownDestinations(graph)

    navigation(
        route = graph.serializedRoute,
        startDestination = graph.startDestination().route.buildRoute(),
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        builder = builder,
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
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    content: @Composable AnimatedVisibilityScope.(NavStackEntry) -> Unit,
) {
    ComposeNavigation.getKnownDestinationsSource().addToKnownDestinations(destination)

    composable(
        destination.route.buildRoute(),
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = { backStackEntry ->
            CompositionLocalProvider(LocalNavDestination provides destination) {
                content(backStackEntry.toNavStackEntry())
            }
        },
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
    content: @Composable (NavStackEntry) -> Unit,
) {
    ComposeNavigation.getKnownDestinationsSource().addToKnownDestinations(destination)

    dialog(
        route = destination.route.buildRoute(),
        arguments = arguments,
        deepLinks = deepLinks,
        dialogProperties = dialogProperties,
        content = { backStackEntry ->
            CompositionLocalProvider(LocalNavDestination provides destination) {
                content(backStackEntry.toNavStackEntry())
            }
        },
    )
}
