//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[GlobalGraph](index.md)

# GlobalGraph

[androidJvm]\
object [GlobalGraph](index.md) : [NavGraph](../-nav-graph/index.md)

You can use this graph to create [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md)s that do not belong to any of your graphs. It might be a bad idea though as it increases chance for your [NavActionVerifier](../../com.adamkobus.compose.navigation/-nav-action-verifier/index.md)s to not work properly.

## Inherited properties

| Name | Summary |
|---|---|
| [graph](../-nav-graph/graph.md) | [androidJvm]<br>open override val [graph](../-nav-graph/graph.md): [NavGraph](../-nav-graph/index.md)<br>In case of [NavGraph](../-nav-graph/index.md), [graph](../-nav-graph/graph.md) always points to itself |
| [name](../-nav-graph/name.md) | [androidJvm]<br>val [name](../-nav-graph/name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [route](../-nav-graph/route.md) | [androidJvm]<br>open override val [route](../-nav-graph/route.md): [NavRoute](../-nav-route/index.md)<br>Represents a route that will be used to register this graph inside NavHost |

## Functions

| Name | Summary |
|---|---|
| [startDestination](start-destination.md) | [androidJvm]<br>open override fun [startDestination](start-destination.md)(): [ScreenDestination](../-screen-destination/index.md)<br>Always returns Root |

## Inherited functions

| Name | Summary |
|---|---|
| [dialogDestination](../-nav-graph/dialog-destination.md) | [androidJvm]<br>fun [dialogDestination](../-nav-graph/dialog-destination.md)(pathName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), init: [NavRoute.Builder](../-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [DialogDestination](../-dialog-destination/index.md)<br>Creates new type-safe builder for [DialogDestination](../-dialog-destination/index.md). It will be initialized with this graph and provided [name](../-nav-graph/name.md) |
| [goTo](../-nav-destination/go-to.md) | [androidJvm]<br>open infix fun [goTo](../-nav-destination/go-to.md)(other: [NavDestination](../-nav-destination/index.md)): [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md)<br>Creates [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md) that originates from this [NavDestination](../-nav-destination/index.md) and targets [other](../-nav-destination/go-to.md). |
| [navIntent](../-nav-destination/nav-intent.md) | [androidJvm]<br>open fun [navIntent](../-nav-destination/nav-intent.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)<br>Creates new [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md). It will be initialized with provided name and this destination as [NavIntent.origin](../../com.adamkobus.compose.navigation.intent/-nav-intent/origin.md) |
| [pop](../-nav-destination/pop.md) | [androidJvm]<br>open fun [pop](../-nav-destination/pop.md)(): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md)<br>Creates [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) that uses this [NavDestination](../-nav-destination/index.md) as a source destination and uses this destination's graph in target [PopDestination](../-pop-destination/index.md)<br>[androidJvm]<br>open infix fun [pop](../-nav-destination/pop.md)(other: [PopDestination](../-pop-destination/index.md)): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md)<br>Creates [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) that originates from this [NavDestination](../-nav-destination/index.md) and targets [other](../-nav-destination/pop.md). |
| [popDestination](../-nav-destination/pop-destination.md) | [androidJvm]<br>open fun [popDestination](../-nav-destination/pop-destination.md)(): [PopDestination](../-pop-destination/index.md)<br>Creates [PopDestination](../-pop-destination/index.md) with this [NavDestination](../-nav-destination/index.md) destination's graph |
| [screenDestination](../-nav-graph/screen-destination.md) | [androidJvm]<br>fun [screenDestination](../-nav-graph/screen-destination.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), init: [NavRoute.Builder](../-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [ScreenDestination](../-screen-destination/index.md)<br>Creates new type-safe builder for [ScreenDestination](../-screen-destination/index.md). It will be initialized with this graph and provided [name](../-nav-graph/screen-destination.md) |
| [toString](../-nav-graph/to-string.md) | [androidJvm]<br>open override fun [toString](../-nav-graph/to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
