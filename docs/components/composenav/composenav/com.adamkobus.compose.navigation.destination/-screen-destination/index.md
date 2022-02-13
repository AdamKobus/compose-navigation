//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[ScreenDestination](index.md)

# ScreenDestination

[androidJvm]\
open class [ScreenDestination](index.md)(graph: [NavGraph](../-nav-graph/index.md), route: [NavRoute](../-nav-route/index.md)) : [NavDestination](../-nav-destination/index.md)

Represents a destination that is displayed as a view in the application

## Parameters

androidJvm

| | |
|---|---|
| graph | Graph that this destination is assigned to |
| route | Unique route that identifies this destination |

## Constructors

| | |
|---|---|
| [ScreenDestination](-screen-destination.md) | [androidJvm]<br>fun [ScreenDestination](-screen-destination.md)(graph: [NavGraph](../-nav-graph/index.md), route: [NavRoute](../-nav-route/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [graph](graph.md) | [androidJvm]<br>open override val [graph](graph.md): [NavGraph](../-nav-graph/index.md) |
| [route](route.md) | [androidJvm]<br>open override val [route](route.md): [NavRoute](../-nav-route/index.md) |

## Functions

| Name | Summary |
|---|---|
| [asDialog](as-dialog.md) | [androidJvm]<br>fun [asDialog](as-dialog.md)(): [DialogDestination](../-dialog-destination/index.md)<br>Converts this destination to [DialogDestination](../-dialog-destination/index.md) |
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Compares with other destination based on [graph](graph.md) and [route](route.md) fields |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Generates hash code based on [graph](graph.md) and [route](route.md) fields |
| [next](next.md) | [androidJvm]<br>fun [next](next.md)(init: [NavRoute.Builder](../-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [ScreenDestination](index.md)<br>Creates a new [ScreenDestination](index.md) with a route that is a continuation of this destination's [route](route.md) |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inherited functions

| Name | Summary |
|---|---|
| [goTo](../-nav-destination/go-to.md) | [androidJvm]<br>open infix fun [goTo](../-nav-destination/go-to.md)(other: [NavDestination](../-nav-destination/index.md)): [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md)<br>Creates [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md) that originates from this [NavDestination](../-nav-destination/index.md) and targets [other](../-nav-destination/go-to.md). |
| [navIntent](../-nav-destination/nav-intent.md) | [androidJvm]<br>open fun [navIntent](../-nav-destination/nav-intent.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)<br>Creates new [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md). It will be initialized with provided name and this destination as [NavIntent.origin](../../com.adamkobus.compose.navigation.intent/-nav-intent/origin.md) |
| [pop](../-nav-destination/pop.md) | [androidJvm]<br>open fun [pop](../-nav-destination/pop.md)(): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md)<br>Creates [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) that uses this [NavDestination](../-nav-destination/index.md) as a source destination and uses this destination's graph in target [PopDestination](../-pop-destination/index.md)<br>[androidJvm]<br>open infix fun [pop](../-nav-destination/pop.md)(other: [PopDestination](../-pop-destination/index.md)): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md)<br>Creates [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) that originates from this [NavDestination](../-nav-destination/index.md) and targets [other](../-nav-destination/pop.md). |
| [popDestination](../-nav-destination/pop-destination.md) | [androidJvm]<br>open fun [popDestination](../-nav-destination/pop-destination.md)(): [PopDestination](../-pop-destination/index.md)<br>Creates [PopDestination](../-pop-destination/index.md) with this [NavDestination](../-nav-destination/index.md) destination's graph |
