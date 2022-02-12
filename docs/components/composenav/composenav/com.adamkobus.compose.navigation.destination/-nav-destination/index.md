//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavDestination](index.md)

# NavDestination

[androidJvm]\
open class [NavDestination](index.md)(graph: [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md), route: [NavRoute](../-nav-route/index.md)) : [INavDestination](../-i-nav-destination/index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [goTo](../-i-nav-destination/go-to.md) | [androidJvm]<br>open infix fun [goTo](../-i-nav-destination/go-to.md)(other: [INavDestination](../-i-nav-destination/index.md)): [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md) |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [next](../-i-nav-destination/next.md) | [androidJvm]<br>open fun [next](../-i-nav-destination/next.md)(init: [NavRoute.Builder](../-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [NavDestination](index.md) |
| [pop](../-i-nav-destination/pop.md) | [androidJvm]<br>open infix fun [pop](../-i-nav-destination/pop.md)(other: [PopDestination](../-pop-destination/index.md)): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [graph](graph.md) | [androidJvm]<br>open override val [graph](graph.md): [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md)<br>Graph to which this destination belongs |
| [route](route.md) | [androidJvm]<br>open override val [route](route.md): [NavRoute](../-nav-route/index.md)<br>Builds a full route definition for this destination. By default it will build a route in format /<name>. |

## Inheritors

| Name |
|---|
| [UnknownDestination](../-unknown-destination/index.md) |
