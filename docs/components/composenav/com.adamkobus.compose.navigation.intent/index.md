//[composenav](../../index.md)/[com.adamkobus.compose.navigation.intent](index.md)

# Package com.adamkobus.compose.navigation.intent

## Types

| Name | Summary |
|---|---|
| [NavIntent](-nav-intent/index.md) | [androidJvm]<br>data class [NavIntent](-nav-intent/index.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), origin: [INavDestination](../com.adamkobus.compose.navigation.destination/-i-nav-destination/index.md)?, arguments: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;)<br>Represents an intent navigate to certain part of the application without knowing the details required to actually perform such action. This mechanism can be used in multi-module apps, tab hosts that have many edge cases, to show dev menu (if it's present), do A/B testing in convenient way and so on. |
