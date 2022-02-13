//[composenav](../../index.md)/[com.adamkobus.compose.navigation.intent](index.md)

# Package com.adamkobus.compose.navigation.intent

## Types

| Name | Summary |
|---|---|
| [NavIntent](-nav-intent/index.md) | [androidJvm]<br>data class [NavIntent](-nav-intent/index.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), origin: [NavDestination](../com.adamkobus.compose.navigation.destination/-nav-destination/index.md)?, popOptions: [NavOptions](../com.adamkobus.compose.navigation.action/-nav-options/index.md)?, arguments: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;)<br>Represents an intent navigate to certain part of the application without knowing the details required to actually perform such action. This mechanism can be used in multi-module apps, tab hosts that have many edge cases, to show dev menu (if it's present), do A/B testing in convenient way and so on. |
| [ResolveResult](-resolve-result/index.md) | [androidJvm]<br>sealed class [ResolveResult](-resolve-result/index.md)<br>Produced as a result of [NavIntent](-nav-intent/index.md) processing by [NavIntentResolver](../com.adamkobus.compose.navigation/-nav-intent-resolver/index.md)s |
