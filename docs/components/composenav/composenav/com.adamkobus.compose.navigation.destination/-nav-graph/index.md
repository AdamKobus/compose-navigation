//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavGraph](index.md)

# NavGraph

[androidJvm]\
abstract class [NavGraph](index.md) : [NavDestination](../-nav-destination/index.md)

[NavGraph](index.md)s are used to group [ScreenDestination](../-screen-destination/index.md)s. Also, each NavHost must be initialized with following information:

- 
   graph's name - provided via [name](name.md)
- 
   start destination - provided via [startDestination](start-destination.md)

## Parameters

androidJvm

| | |
|---|---|
| name | It is used to identify this graph. All destinations created in this graph will also be prefixed by the graph's name. |

## Constructors

| | |
|---|---|
| [NavGraph](-nav-graph.md) | [androidJvm]<br>fun [NavGraph](-nav-graph.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [graph](graph.md) | [androidJvm]<br>open override val [graph](graph.md): [NavGraph](index.md)<br>In case of [NavGraph](index.md), [graph](graph.md) always points to itself |
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [route](route.md) | [androidJvm]<br>open override val [route](route.md): [NavRoute](../-nav-route/index.md)<br>Represents a route that will be used to register this graph inside NavHost |

## Functions

| Name | Summary |
|---|---|
| [dialogDestination](dialog-destination.md) | [androidJvm]<br>fun [dialogDestination](dialog-destination.md)(pathName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), init: [NavRoute.Builder](../-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [DialogDestination](../-dialog-destination/index.md)<br>Creates new type-safe builder for [DialogDestination](../-dialog-destination/index.md). It will be initialized with this graph and provided [name](name.md) |
| [screenDestination](screen-destination.md) | [androidJvm]<br>fun [screenDestination](screen-destination.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), init: [NavRoute.Builder](../-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [ScreenDestination](../-screen-destination/index.md)<br>Creates new type-safe builder for [ScreenDestination](../-screen-destination/index.md). It will be initialized with this graph and provided [name](screen-destination.md) |
| [startDestination](start-destination.md) | [androidJvm]<br>abstract fun [startDestination](start-destination.md)(): [NavDestination](../-nav-destination/index.md)<br>Each graph must have a start destination which NavHostController will try to display when you navigate to this graph. It's ok to have another graph as start destination, but eventually one of the graphs should return [ScreenDestination](../-screen-destination/index.md). |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inherited functions

| Name | Summary |
|---|---|
| [goTo](../-nav-destination/go-to.md) | [androidJvm]<br>open infix fun [goTo](../-nav-destination/go-to.md)(other: [NavDestination](../-nav-destination/index.md)): [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md)<br>Creates [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md) that originates from this [NavDestination](../-nav-destination/index.md) and targets [other](../-nav-destination/go-to.md). |
| [navIntent](../-nav-destination/nav-intent.md) | [androidJvm]<br>open fun [navIntent](../-nav-destination/nav-intent.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)<br>Creates new [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md). It will be initialized with provided name and this destination as [NavIntent.origin](../../com.adamkobus.compose.navigation.intent/-nav-intent/origin.md) |
| [pop](../-nav-destination/pop.md) | [androidJvm]<br>open fun [pop](../-nav-destination/pop.md)(): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md)<br>Creates [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) that uses this [NavDestination](../-nav-destination/index.md) as a source destination and uses this destination's graph in target [PopDestination](../-pop-destination/index.md)<br>[androidJvm]<br>open infix fun [pop](../-nav-destination/pop.md)(other: [PopDestination](../-pop-destination/index.md)): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md)<br>Creates [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) that originates from this [NavDestination](../-nav-destination/index.md) and targets [other](../-nav-destination/pop.md). |
| [popDestination](../-nav-destination/pop-destination.md) | [androidJvm]<br>open fun [popDestination](../-nav-destination/pop-destination.md)(): [PopDestination](../-pop-destination/index.md)<br>Creates [PopDestination](../-pop-destination/index.md) with this [NavDestination](../-nav-destination/index.md) destination's graph |
| [popUpTo](../-nav-destination/pop-up-to.md) | [androidJvm]<br>open fun [popUpTo](../-nav-destination/pop-up-to.md)(destination: [NavDestination](../-nav-destination/index.md), inclusive: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, saveState: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [PopUpToAction](../../com.adamkobus.compose.navigation.action/-pop-up-to-action/index.md)<br>Creates [PopUpToAction](../../com.adamkobus.compose.navigation.action/-pop-up-to-action/index.md) |

## Inheritors

| Name |
|---|
| [GlobalGraph](../-global-graph/index.md) |
| [UnknownGraph](../-unknown-graph/index.md) |
