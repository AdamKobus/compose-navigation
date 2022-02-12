//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[UnknownDestination](index.md)

# UnknownDestination

[androidJvm]\
class [UnknownDestination](index.md) : [NavDestination](../-nav-destination/index.md)

## Functions

| Name | Summary |
|---|---|
| [equals](../-nav-destination/equals.md) | [androidJvm]<br>open operator override fun [equals](../-nav-destination/equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [goTo](../-i-nav-destination/go-to.md) | [androidJvm]<br>open infix fun [goTo](../-i-nav-destination/go-to.md)(other: [INavDestination](../-i-nav-destination/index.md)): [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md) |
| [hashCode](../-nav-destination/hash-code.md) | [androidJvm]<br>open override fun [hashCode](../-nav-destination/hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [next](../-i-nav-destination/next.md) | [androidJvm]<br>open fun [next](../-i-nav-destination/next.md)(init: [NavRoute.Builder](../-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [NavDestination](../-nav-destination/index.md) |
| [pop](../-i-nav-destination/pop.md) | [androidJvm]<br>open infix fun [pop](../-i-nav-destination/pop.md)(other: [PopDestination](../-pop-destination/index.md)): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [graph](../-nav-destination/graph.md) | [androidJvm]<br>open override val [graph](../-nav-destination/graph.md): [NavGraph](../../com.adamkobus.compose.navigation.data/-nav-graph/index.md)<br>Graph to which this destination belongs |
| [path](path.md) | [androidJvm]<br>val [path](path.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [route](../-nav-destination/route.md) | [androidJvm]<br>open override val [route](../-nav-destination/route.md): [NavRoute](../-nav-route/index.md)<br>Builds a full route definition for this destination. By default it will build a route in format /<name>. |
