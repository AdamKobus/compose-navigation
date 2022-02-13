//[composenav](../../index.md)/[com.adamkobus.compose.navigation.destination](index.md)

# Package com.adamkobus.compose.navigation.destination

## Types

| Name | Summary |
|---|---|
| [CurrentDestination](-current-destination/index.md) | [androidJvm]<br>data class [CurrentDestination](-current-destination/index.md)(destination: [NavDestination](-nav-destination/index.md)?, backStack: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavDestination](-nav-destination/index.md)&gt;) |
| [DialogDestination](-dialog-destination/index.md) | [androidJvm]<br>open class [DialogDestination](-dialog-destination/index.md)(graph: [NavGraph](-nav-graph/index.md), route: [NavRoute](-nav-route/index.md)) : [NavDestination](-nav-destination/index.md)<br>Represents a destination that is displayed as a dialog in the application |
| [GlobalGraph](-global-graph/index.md) | [androidJvm]<br>object [GlobalGraph](-global-graph/index.md) : [NavGraph](-nav-graph/index.md)<br>You can use this graph to create [NavigateAction](../com.adamkobus.compose.navigation.action/-navigate-action/index.md)s that do not belong to any of your graphs. It might be a bad idea though as it increases chance for your [NavActionVerifier](../com.adamkobus.compose.navigation/-nav-action-verifier/index.md)s to not work properly. |
| [INavDestination](-i-nav-destination/index.md) | [androidJvm]<br>interface [INavDestination](-i-nav-destination/index.md)<br>Base type for all of the destinations. |
| [NavDestination](-nav-destination/index.md) | [androidJvm]<br>interface [NavDestination](-nav-destination/index.md) : [INavDestination](-i-nav-destination/index.md)<br>Represents a destination that can be added to back stack |
| [NavGraph](-nav-graph/index.md) | [androidJvm]<br>abstract class [NavGraph](-nav-graph/index.md) : [NavDestination](-nav-destination/index.md)<br>[NavGraph](-nav-graph/index.md)s are used to group [ScreenDestination](-screen-destination/index.md)s. Also, each NavHost must be initialized with following information: |
| [NavRoute](-nav-route/index.md) | [androidJvm]<br>data class [NavRoute](-nav-route/index.md)(parts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavRoutePart](-nav-route-part/index.md)&gt;, separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Creates a definition of a route representing a destination in your application. |
| [NavRoutePart](-nav-route-part/index.md) | [androidJvm]<br>sealed class [NavRoutePart](-nav-route-part/index.md)<br>Used to declare apart of [ScreenDestination](-screen-destination/index.md)'s route.. |
| [PopDestination](-pop-destination/index.md) | [androidJvm]<br>data class [PopDestination](-pop-destination/index.md)(graph: [NavGraph](-nav-graph/index.md), route: [NavRoute](-nav-route/index.md)) : [INavDestination](-i-nav-destination/index.md)<br>This is a special kind of destination that can be used only as a target of [PopAction](../com.adamkobus.compose.navigation.action/-pop-action/index.md) |
| [ScreenDestination](-screen-destination/index.md) | [androidJvm]<br>open class [ScreenDestination](-screen-destination/index.md)(graph: [NavGraph](-nav-graph/index.md), route: [NavRoute](-nav-route/index.md)) : [NavDestination](-nav-destination/index.md)<br>Represents a destination that is displayed as a view in the application |
| [UnknownDestination](-unknown-destination/index.md) | [androidJvm]<br>data class [UnknownDestination](-unknown-destination/index.md) : [NavDestination](-nav-destination/index.md)<br>Represents a destination that was not recognized by [NavigationStateSource](../com.adamkobus.compose.navigation/-navigation-state-source/index.md). |
| [UnknownGraph](-unknown-graph/index.md) | [androidJvm]<br>object [UnknownGraph](-unknown-graph/index.md) : [NavGraph](-nav-graph/index.md)<br>Any destination that is not recognized by [NavigationStateSource](../com.adamkobus.compose.navigation/-navigation-state-source/index.md) is assigned to this graph. |

## Functions

| Name | Summary |
|---|---|
| [navRoute](nav-route.md) | [androidJvm]<br>fun [navRoute](nav-route.md)(graphName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), init: [NavRoute.Builder](-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [NavRoute](-nav-route/index.md)<br>[androidJvm]<br>fun [navRoute](nav-route.md)(graphName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), init: [NavRoute.Builder](-nav-route/-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [NavRoute](-nav-route/index.md)<br>Initializes type-safe builder for [NavRoute](-nav-route/index.md) |
