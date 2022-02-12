//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.data](../index.md)/[NavGraph](index.md)

# NavGraph

[androidJvm]\
abstract class [NavGraph](index.md) : [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md)

## Constructors

| | |
|---|---|
| [NavGraph](-nav-graph.md) | [androidJvm]<br>fun [NavGraph](-nav-graph.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [goTo](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/go-to.md) | [androidJvm]<br>open infix fun [goTo](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/go-to.md)(other: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md)): [NavigateAction](../../com.adamkobus.compose.navigation.action/-navigate-action/index.md) |
| [navDestination](nav-destination.md) | [androidJvm]<br>fun [navDestination](nav-destination.md)(pathName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), init: [NavRoute.Builder](../../com.adamkobus.compose.navigation.destination/-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md) |
| [navIntent](nav-intent.md) | [androidJvm]<br>fun [navIntent](nav-intent.md)(pathName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md) |
| [next](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/next.md) | [androidJvm]<br>open fun [next](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/next.md)(init: [NavRoute.Builder](../../com.adamkobus.compose.navigation.destination/-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md) |
| [pop](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/pop.md) | [androidJvm]<br>open infix fun [pop](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/pop.md)(other: [PopDestination](../../com.adamkobus.compose.navigation.destination/-pop-destination/index.md)): [PopAction](../../com.adamkobus.compose.navigation.action/-pop-action/index.md) |
| [popDestination](pop-destination.md) | [androidJvm]<br>fun [popDestination](pop-destination.md)(): [PopDestination](../../com.adamkobus.compose.navigation.destination/-pop-destination/index.md) |
| [startDestination](start-destination.md) | [androidJvm]<br>abstract fun [startDestination](start-destination.md)(): [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md) |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [graph](graph.md) | [androidJvm]<br>open override val [graph](graph.md): [NavGraph](index.md)<br>Graph to which this destination belongs |
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [route](route.md) | [androidJvm]<br>open override val [route](route.md): [NavRoute](../../com.adamkobus.compose.navigation.destination/-nav-route/index.md)<br>Builds a full route definition for this destination. By default it will build a route in format /<name>. |

## Inheritors

| Name |
|---|
| [GlobalGraph](../-global-graph/index.md) |
| [UnknownGraph](../-unknown-graph/index.md) |
