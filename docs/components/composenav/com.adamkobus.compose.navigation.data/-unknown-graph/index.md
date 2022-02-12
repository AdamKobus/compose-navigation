//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.data](../index.md)/[UnknownGraph](index.md)

# UnknownGraph

[androidJvm]\
object [UnknownGraph](index.md) : [NavGraph](../-nav-graph/index.md)

## Functions

| Name | Summary |
|---|---|
| [goTo](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/go-to.md) | [androidJvm]<br>open infix fun [goTo](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/go-to.md)(other: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md)): [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md) |
| [navDestination](../-nav-graph/nav-destination.md) | [androidJvm]<br>fun [navDestination](../-nav-graph/nav-destination.md)(pathName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), init: [NavRoute.Builder](../../com.adamkobus.compose.navigation.destination/-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md) |
| [navIntent](../-nav-graph/nav-intent.md) | [androidJvm]<br>fun [navIntent](../-nav-graph/nav-intent.md)(pathName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md) |
| [next](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/next.md) | [androidJvm]<br>open fun [next](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/next.md)(init: [NavRoute.Builder](../../com.adamkobus.compose.navigation.destination/-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md) |
| [pop](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/pop.md) | [androidJvm]<br>open infix fun [pop](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/pop.md)(other: [PopDestination](../../com.adamkobus.compose.navigation.destination/-pop-destination/index.md)): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) |
| [popDestination](../-nav-graph/pop-destination.md) | [androidJvm]<br>fun [popDestination](../-nav-graph/pop-destination.md)(): [PopDestination](../../com.adamkobus.compose.navigation.destination/-pop-destination/index.md) |
| [startDestination](start-destination.md) | [androidJvm]<br>open override fun [startDestination](start-destination.md)(): [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md) |
| [toString](../-nav-graph/to-string.md) | [androidJvm]<br>open override fun [toString](../-nav-graph/to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [graph](../-nav-graph/graph.md) | [androidJvm]<br>open override val [graph](../-nav-graph/graph.md): [NavGraph](../-nav-graph/index.md)<br>Graph to which this destination belongs |
| [name](../-nav-graph/name.md) | [androidJvm]<br>val [name](../-nav-graph/name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [route](../-nav-graph/route.md) | [androidJvm]<br>open override val [route](../-nav-graph/route.md): [NavRoute](../../com.adamkobus.compose.navigation.destination/-nav-route/index.md)<br>Builds a full route definition for this destination. By default it will build a route in format /<name>. |
