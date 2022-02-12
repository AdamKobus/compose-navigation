//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[INavDestination](index.md)

# INavDestination

[androidJvm]\
interface [INavDestination](index.md)

## Functions

| Name | Summary |
|---|---|
| [goTo](go-to.md) | [androidJvm]<br>open infix fun [goTo](go-to.md)(other: [INavDestination](index.md)): [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md) |
| [next](next.md) | [androidJvm]<br>open fun [next](next.md)(init: [NavRoute.Builder](../-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [NavDestination](../-nav-destination/index.md) |
| [pop](pop.md) | [androidJvm]<br>open infix fun [pop](pop.md)(other: [PopDestination](../-pop-destination/index.md)): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) |

## Properties

| Name | Summary |
|---|---|
| [graph](graph.md) | [androidJvm]<br>abstract val [graph](graph.md): [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md)<br>Graph to which this destination belongs |
| [route](route.md) | [androidJvm]<br>abstract val [route](route.md): [NavRoute](../-nav-route/index.md)<br>Builds a full route definition for this destination. By default it will build a route in format /<name>. |

## Inheritors

| Name |
|---|
| [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md) |
| [NavDestination](../-nav-destination/index.md) |
| [PopDestination](../-pop-destination/index.md) |
