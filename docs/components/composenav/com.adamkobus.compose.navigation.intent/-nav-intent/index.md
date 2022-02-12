//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.intent](../index.md)/[NavIntent](index.md)

# NavIntent

[androidJvm]\
data class [NavIntent](index.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), origin: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md)?, arguments: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;)

Represents an intent navigate to certain part of the application without knowing the details required to actually perform such action. This mechanism can be used in multi-module apps, tab hosts that have many edge cases, to show dev menu (if it's present), do A/B testing in convenient way and so on.

Intent on its own is not enough for navigation to happen. You must also register s in [ComposeNavigation](../../com.adamkobus.compose.navigation/-compose-navigation/index.md)

## Constructors

| | |
|---|---|
| [NavIntent](-nav-intent.md) | [androidJvm]<br>fun [NavIntent](-nav-intent.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), origin: [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md)? = null, arguments: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; = mutableMapOf()) |

## Functions

| Name | Summary |
|---|---|
| [addArgument](add-argument.md) | [androidJvm]<br>fun [addArgument](add-argument.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [NavIntent](index.md) |
| [arg](arg.md) | [androidJvm]<br>infix fun [arg](arg.md)(value: [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;): [NavIntent](index.md) |
| [asResult](as-result.md) | [androidJvm]<br>fun [asResult](as-result.md)(): [ResolveResult](../../com.adamkobus.compose.navigation.data/-resolve-result/index.md) |
| [get](get.md) | [androidJvm]<br>operator fun [get](get.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? |
| [getInt](get-int.md) | [androidJvm]<br>fun [getInt](get-int.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? |
| [getString](get-string.md) | [androidJvm]<br>fun [getString](get-string.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [plus](plus.md) | [androidJvm]<br>operator fun [plus](plus.md)(value: [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;): [NavIntent](index.md) |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [origin](origin.md) | [androidJvm]<br>val [origin](origin.md): [INavDestination](../../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md)? = null |
