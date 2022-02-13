//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavRoute](index.md)/[buildPath](build-path.md)

# buildPath

[androidJvm]\
fun [buildPath](build-path.md)(vararg params: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

#### Return

Path built based on the route definition and provided [params](build-path.md)

## Throws

| | |
|---|---|
| [kotlin.IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-argument-exception/index.html) | if number of provided params doesn't match the expected params count. |

[androidJvm]\
fun [buildPath](build-path.md)(params: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

#### Return

Path build based on the route definition and provided [params](build-path.md)

## Throws

| | |
|---|---|
| [kotlin.IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-argument-exception/index.html) | if number of provided params doesn't match the expected params count. |
