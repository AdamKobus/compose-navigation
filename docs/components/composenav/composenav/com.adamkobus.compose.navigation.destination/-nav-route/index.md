//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavRoute](index.md)

# NavRoute

[androidJvm]\
data class [NavRoute](index.md)(parts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavRoutePart](../-nav-route-part/index.md)&gt;, separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Creates a definition of a route representing a destination in your application.

## Parameters

androidJvm

| | |
|---|---|
| parts | Initial parts |
| separator | By default, Compose Navigation uses "/" as separator. Custom ones were not tested yet. |

## Constructors

| | |
|---|---|
| [NavRoute](-nav-route.md) | [androidJvm]<br>fun [NavRoute](-nav-route.md)(parts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavRoutePart](../-nav-route-part/index.md)&gt;, separator: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | [androidJvm]<br>class [Builder](-builder/index.md)<br>A builder for [NavRoute](index.md) |
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [buildPath](build-path.md) | [androidJvm]<br>fun [buildPath](build-path.md)(vararg params: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>fun [buildPath](build-path.md)(params: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [buildRoute](build-route.md) | [androidJvm]<br>fun [buildRoute](build-route.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [next](next.md) | [androidJvm]<br>fun [next](next.md)(init: [NavRoute.Builder](-builder/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}): [NavRoute](index.md)<br>Starts building a new route using builder that is populated with [NavRoutePart](../-nav-route-part/index.md)s from the [NavRoute](index.md) on which [next](next.md) was invoked. |
