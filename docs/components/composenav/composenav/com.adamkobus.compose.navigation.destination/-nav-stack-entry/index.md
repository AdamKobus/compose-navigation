//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.destination](../index.md)/[NavStackEntry](index.md)

# NavStackEntry

[androidJvm]\
data class [NavStackEntry](index.md)(destination: [NavDestination](../-nav-destination/index.md), arguments: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;)

This class holds information about single entry in navigation back stack.

If destination has any arguments in its route, then it's guaranteed that their values will be present in [arguments](arguments.md). That's because Compose Navigation library does not support optional arguments by design. Assumption is that any param that has been declared in the route must have a value assigned during navigation.

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

## Functions

| Name | Summary |
|---|---|
| [getBoolean](get-boolean.md) | [androidJvm]<br>fun [getBoolean](get-boolean.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getDouble](get-double.md) | [androidJvm]<br>fun [getDouble](get-double.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [getFloat](get-float.md) | [androidJvm]<br>fun [getFloat](get-float.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) |
| [getInt](get-int.md) | [androidJvm]<br>fun [getInt](get-int.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getLong](get-long.md) | [androidJvm]<br>fun [getLong](get-long.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [getString](get-string.md) | [androidJvm]<br>fun [getString](get-string.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns a String representation of [NavStackEntry](index.md) |
