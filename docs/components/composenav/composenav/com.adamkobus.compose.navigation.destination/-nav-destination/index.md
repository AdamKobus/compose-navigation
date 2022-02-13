//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavDestination](index.md)

# NavDestination

[androidJvm]\
interface [NavDestination](index.md) : [INavDestination](../-i-nav-destination/index.md)

Represents a destination that can be added to back stack

## Inherited properties

| Name | Summary |
|---|---|
| [graph](../-i-nav-destination/graph.md) | [androidJvm]<br>abstract val [graph](../-i-nav-destination/graph.md): [NavGraph](../-nav-graph/index.md)<br>Graph that this destination belongs to |
| [route](../-i-nav-destination/route.md) | [androidJvm]<br>abstract val [route](../-i-nav-destination/route.md): [NavRoute](../-nav-route/index.md)<br>Each destination must have a unique route. |

## Functions

| Name | Summary |
|---|---|
| [goTo](go-to.md) | [androidJvm]<br>open infix fun [goTo](go-to.md)(other: [NavDestination](index.md)): [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md)<br>Creates [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md) that originates from this [NavDestination](index.md) and targets [other](go-to.md). |
| [navIntent](nav-intent.md) | [androidJvm]<br>open fun [navIntent](nav-intent.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)<br>Creates new [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md). It will be initialized with provided name and this destination as [NavIntent.origin](../../com.adamkobus.compose.navigation.intent/-nav-intent/origin.md) |
| [pop](pop.md) | [androidJvm]<br>open fun [pop](pop.md)(): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md)<br>Creates [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) that uses this [NavDestination](index.md) as a source destination and uses this destination's graph in target [PopDestination](../-pop-destination/index.md)<br>[androidJvm]<br>open infix fun [pop](pop.md)(other: [PopDestination](../-pop-destination/index.md)): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md)<br>Creates [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) that originates from this [NavDestination](index.md) and targets [other](pop.md). |
| [popDestination](pop-destination.md) | [androidJvm]<br>open fun [popDestination](pop-destination.md)(): [PopDestination](../-pop-destination/index.md)<br>Creates [PopDestination](../-pop-destination/index.md) with this [NavDestination](index.md) destination's graph |

## Inheritors

| Name |
|---|
| [DialogDestination](../-dialog-destination/index.md) |
| [NavGraph](../-nav-graph/index.md) |
| [ScreenDestination](../-screen-destination/index.md) |
| [UnknownDestination](../-unknown-destination/index.md) |
