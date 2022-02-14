//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavStackEntry](index.md)

# NavStackEntry

[androidJvm]\
data class [NavStackEntry](index.md)(destination: [NavDestination](../-nav-destination/index.md), arguments: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;)

This class holds information about single entry in navigation back stack.

If destination has any arguments in its route, then it's guaranteed that their values will be present in [arguments](arguments.md). That's because Compose Navigation library does not support optional arguments by design. Assumption is that any param that has been declared in the route always receive value. It being optional should be indicated in the serialized value's string itself, i.e. by serializing the value to strings like "missing" and "present(42)"

## Parameters

androidJvm

| | |
|---|---|
| destination | destination linked to this back stack entry |
| arguments | args with which this destination was launched |

## Constructors

| | |
|---|---|
| [NavStackEntry](-nav-stack-entry.md) | [androidJvm]<br>fun [NavStackEntry](-nav-stack-entry.md)(destination: [NavDestination](../-nav-destination/index.md), arguments: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [arguments](arguments.md) | [androidJvm]<br>val [arguments](arguments.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [destination](destination.md) | [androidJvm]<br>val [destination](destination.md): [NavDestination](../-nav-destination/index.md) |

## Extensions

| Name | Summary |
|---|---|
| [getInt](../get-int.md) | [androidJvm]<br>fun [NavStackEntry](index.md)?.[getInt](../get-int.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getLong](../get-long.md) | [androidJvm]<br>fun [NavStackEntry](index.md)?.[getLong](../get-long.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [getString](../get-string.md) | [androidJvm]<br>fun [NavStackEntry](index.md)?.[getString](../get-string.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
