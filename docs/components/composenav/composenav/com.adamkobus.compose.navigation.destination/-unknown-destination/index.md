//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[UnknownDestination](index.md)

# UnknownDestination

[androidJvm]\
data class [UnknownDestination](index.md) : [NavDestination](../-nav-destination/index.md)

Represents a destination that was not recognized by [NavigationStateSource](../../com.adamkobus.compose.navigation/-navigation-state-source/index.md).

## Parameters

androidJvm

| | |
|---|---|
| path | Raw path string that was encountered by [NavigationStateSource](../../com.adamkobus.compose.navigation/-navigation-state-source/index.md) |

## Properties

| Name | Summary |
|---|---|
| [graph](graph.md) | [androidJvm]<br>open override val [graph](graph.md): [NavGraph](../-nav-graph/index.md)<br>Fixed value - [UnknownGraph](../-unknown-graph/index.md) |
| [path](path.md) | [androidJvm]<br>val [path](path.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [route](route.md) | [androidJvm]<br>open override val [route](route.md): [NavRoute](../-nav-route/index.md)<br>Navigating to this route will most likely produce crash. This destination is purely for informational purpose and should not be navigated to. The actual path that was encountered can be accessed via [path](path.md) param |

## Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Inherited functions

| Name | Summary |
|---|---|
| [goTo](../-nav-destination/go-to.md) | [androidJvm]<br>open infix fun [goTo](../-nav-destination/go-to.md)(other: [NavDestination](../-nav-destination/index.md)): [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md)<br>Creates [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md) that originates from this [NavDestination](../-nav-destination/index.md) and targets [other](../-nav-destination/go-to.md). |
| [navIntent](../-nav-destination/nav-intent.md) | [androidJvm]<br>open fun [navIntent](../-nav-destination/nav-intent.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)<br>Creates new [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md). It will be initialized with provided name and this destination as [NavIntent.origin](../../com.adamkobus.compose.navigation.intent/-nav-intent/origin.md) |
| [pop](../-nav-destination/pop.md) | [androidJvm]<br>open fun [pop](../-nav-destination/pop.md)(): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md)<br>Creates [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) that uses this [NavDestination](../-nav-destination/index.md) as a source destination and uses this destination's graph in target [PopDestination](../-pop-destination/index.md)<br>[androidJvm]<br>open infix fun [pop](../-nav-destination/pop.md)(other: [PopDestination](../-pop-destination/index.md)): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md)<br>Creates [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) that originates from this [NavDestination](../-nav-destination/index.md) and targets [other](../-nav-destination/pop.md). |
| [popDestination](../-nav-destination/pop-destination.md) | [androidJvm]<br>open fun [popDestination](../-nav-destination/pop-destination.md)(): [PopDestination](../-pop-destination/index.md)<br>Creates [PopDestination](../-pop-destination/index.md) with this [NavDestination](../-nav-destination/index.md) destination's graph |
| [popUpTo](../-nav-destination/pop-up-to.md) | [androidJvm]<br>open fun [popUpTo](../-nav-destination/pop-up-to.md)(destination: [NavDestination](../-nav-destination/index.md), inclusive: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, saveState: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [PopUpToAction](../../com.adamkobus.compose.navigation.action/-pop-up-to-action/index.md)<br>Creates [PopUpToAction](../../com.adamkobus.compose.navigation.action/-pop-up-to-action/index.md) |
