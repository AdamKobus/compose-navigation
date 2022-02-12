//[composenav](../../index.md)/[com.adamkobus.compose.navigation.destination](index.md)

# Package com.adamkobus.compose.navigation.destination

## Types

| Name | Summary |
|---|---|
| [CurrentDestination](-current-destination/index.md) | [androidJvm]<br>data class [CurrentDestination](-current-destination/index.md)(destination: [INavDestination](-i-nav-destination/index.md)?, backStack: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[INavDestination](-i-nav-destination/index.md)&gt;) |
| [INavDestination](-i-nav-destination/index.md) | [androidJvm]<br>interface [INavDestination](-i-nav-destination/index.md) |
| [NavDestination](-nav-destination/index.md) | [androidJvm]<br>open class [NavDestination](-nav-destination/index.md)(graph: [NavGraph](../com.adamkobus.compose.navigation.data/-nav-graph/index.md), route: [NavRoute](-nav-route/index.md)) : [INavDestination](-i-nav-destination/index.md) |
| [NavRoute](-nav-route/index.md) | [androidJvm]<br>data class [NavRoute](-nav-route/index.md)(parts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavRoutePart](-nav-route-part/index.md)&gt;, separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Creates a definition of a route representing a destination in your application. |
| [NavRoutePart](-nav-route-part/index.md) | [androidJvm]<br>sealed class [NavRoutePart](-nav-route-part/index.md) |
| [PopDestination](-pop-destination/index.md) | [androidJvm]<br>data class [PopDestination](-pop-destination/index.md)(graph: [NavGraph](../com.adamkobus.compose.navigation.data/-nav-graph/index.md), route: [NavRoute](-nav-route/index.md)) : [INavDestination](-i-nav-destination/index.md) |
| [UnknownDestination](-unknown-destination/index.md) | [androidJvm]<br>class [UnknownDestination](-unknown-destination/index.md) : [NavDestination](-nav-destination/index.md) |

## Functions

| Name | Summary |
|---|---|
| [navRoute](nav-route.md) | [androidJvm]<br>fun [navRoute](nav-route.md)(graphName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), init: [NavRoute.Builder](-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [NavRoute](-nav-route/index.md)<br>fun [navRoute](nav-route.md)(graphName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), init: [NavRoute.Builder](-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [NavRoute](-nav-route/index.md) |
