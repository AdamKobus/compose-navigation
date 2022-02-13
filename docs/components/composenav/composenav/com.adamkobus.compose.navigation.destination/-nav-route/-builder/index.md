//[composenav](../../../../index.md)/[com.adamkobus.compose.navigation.destination](../../index.md)/[NavRoute](../index.md)/[Builder](index.md)

# Builder

[androidJvm]\
class [Builder](index.md)

A builder for [NavRoute](../index.md)

## Constructors

| | |
|---|---|
| [Builder](-builder.md) | [androidJvm]<br>fun [Builder](-builder.md)(initialParts: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[NavRoutePart](../../-nav-route-part/index.md)&gt;)<br>Initializes the builder with a list of initial route parts. |
| [Builder](-builder.md) | [androidJvm]<br>fun [Builder](-builder.md)(graphName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Initializes the builder with single path part representing the graph's name |
| [Builder](-builder.md) | [androidJvm]<br>fun [Builder](-builder.md)(graphName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), pathName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Initializes the builder with two parts: graph's name and static path. |

## Properties

| Name | Summary |
|---|---|
| [separator](separator.md) | [androidJvm]<br>var [separator](separator.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | [androidJvm]<br>fun [build](build.md)(): [NavRoute](../index.md)<br>Creates new [NavRoute](../index.md) based on configured path parts. |
| [param](param.md) | [androidJvm]<br>fun [param](param.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Adds a param to the route builder. In the route itself it will be rendered as {<name>}. When performing navigation, this will be replaced by a param's value. |
| [path](path.md) | [androidJvm]<br>fun [path](path.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Adds new static path to the route builder. |
