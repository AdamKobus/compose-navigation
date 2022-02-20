//[composenav](../../index.md)/[com.adamkobus.compose.navigation](index.md)/[ComposeNavHost](-compose-nav-host.md)

# ComposeNavHost

[androidJvm]\

@Composable

fun [ComposeNavHost](-compose-nav-host.md)(startGraph: [NavGraph](../com.adamkobus.compose.navigation.destination/-nav-graph/index.md), controller: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html), navigationId: [NavigationId](-navigation-id/index.md), modifier: Modifier = Modifier, contentAlignment: Alignment = Alignment.Center, enterTransition: AnimatedContentScope&lt;[NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html)&gt;.() -&gt; EnterTransition = { fadeIn(animationSpec = tween(DEFAULT_ANIM_DURATION_MS)) }, exitTransition: AnimatedContentScope&lt;[NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html)&gt;.() -&gt; ExitTransition = { fadeOut(animationSpec = tween(DEFAULT_ANIM_DURATION_MS)) }, popEnterTransition: AnimatedContentScope&lt;[NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html)&gt;.() -&gt; EnterTransition = enterTransition, popExitTransition: AnimatedContentScope&lt;[NavBackStackEntry](https://developer.android.com/reference/kotlin/androidx/navigation/NavBackStackEntry.html)&gt;.() -&gt; ExitTransition = exitTransition, builder: [NavGraphBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavGraphBuilder.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

- 

Configures nested AnimatedNavHost

## See also

androidJvm

| | |
|---|---|
| AnimatedNavHost |  |

## Parameters

androidJvm

| | |
|---|---|
| startGraph | This graph's [NavGraph.startDestination](../com.adamkobus.compose.navigation.destination/-nav-graph/start-destination.md) will be the first navigation displayed to the user. |
