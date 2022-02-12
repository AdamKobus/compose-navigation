//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[PopDestination](index.md)

# PopDestination

[androidJvm]\
data class [PopDestination](index.md)(graph: [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md), route: [NavRoute](../-nav-route/index.md)) : [INavDestination](../-i-nav-destination/index.md)

## Functions

| Name | Summary |
|---|---|
| [goTo](go-to.md) | [androidJvm]<br>open infix override fun [goTo](go-to.md)(other: [INavDestination](../-i-nav-destination/index.md)): [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md) |
| [next](next.md) | [androidJvm]<br>open override fun [next](next.md)(init: [NavRoute.Builder](../-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [NavDestination](../-nav-destination/index.md) |
| [pop](pop.md) | [androidJvm]<br>open infix override fun [pop](pop.md)(other: [PopDestination](index.md)): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [graph](graph.md) | [androidJvm]<br>open override val [graph](graph.md): [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md)<br>Graph to which this destination belongs |
| [route](route.md) | [androidJvm]<br>open override val [route](route.md): [NavRoute](../-nav-route/index.md)<br>Builds a full route definition for this destination. By default it will build a route in format /<name>. |
