//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.intent](../index.md)/[NavIntent](index.md)

# NavIntent

[androidJvm]\
data class [NavIntent](index.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), origin: [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md)?, popOptions: [NavOptions](../../com.adamkobus.compose.navigation.action/-nav-options/index.md)?, navigationId: [NavigationId](../../com.adamkobus.compose.navigation/-navigation-id/index.md)?, arguments: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;)

Represents an intent navigate to certain part of the application without knowing the details required to actually perform such action. This mechanism can be used in multi-module apps, tab hosts that have many edge cases, to show dev menu (if it's present), do A/B testing in convenient way and so on.

Intent on its own is not enough for navigation to happen. You must also register [NavIntentResolver](../../com.adamkobus.compose.navigation/-nav-intent-resolver/index.md) in [ComposeNavigation](../../com.adamkobus.compose.navigation/-compose-navigation/index.md)

## Parameters

androidJvm

| | |
|---|---|
| name | Unique id of the intent |
| origin | to help [NavIntentResolver](../../com.adamkobus.compose.navigation/-nav-intent-resolver/index.md)s process this NavIntent, you can provide a destination that requested it. |
| popOptions | if this intent should close a flow, then you can provide options that should result in doing that. Example could be an intent that launches home screen after successful log in. Such intent would contain [popOptions](pop-options.md) that remove log in graph. |
| navigationId | Might be helpful to some [NavIntentResolver](../../com.adamkobus.compose.navigation/-nav-intent-resolver/index.md)s to create nav action |
| arguments | provides a way to add some extra information to the intent |

## Constructors

| | |
|---|---|
| [NavIntent](-nav-intent.md) | [androidJvm]<br>fun [NavIntent](-nav-intent.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), origin: [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md)? = null, popOptions: [NavOptions](../../com.adamkobus.compose.navigation.action/-nav-options/index.md)? = null, navigationId: [NavigationId](../../com.adamkobus.compose.navigation/-navigation-id/index.md)? = null, arguments: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; = mutableMapOf()) |

## Properties

| Name | Summary |
|---|---|
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [navigationId](navigation-id.md) | [androidJvm]<br>val [navigationId](navigation-id.md): [NavigationId](../../com.adamkobus.compose.navigation/-navigation-id/index.md)? = null |
| [origin](origin.md) | [androidJvm]<br>val [origin](origin.md): [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md)? = null |
| [popOptions](pop-options.md) | [androidJvm]<br>val [popOptions](pop-options.md): [NavOptions](../../com.adamkobus.compose.navigation.action/-nav-options/index.md)? = null |

## Functions

| Name | Summary |
|---|---|
| [addArgument](add-argument.md) | [androidJvm]<br>fun [addArgument](add-argument.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)): [NavIntent](index.md)<br>Creates a copy of [NavIntent](index.md) with ([key](add-argument.md), [value](add-argument.md)) pair added to its arguments |
| [arg](arg.md) | [androidJvm]<br>infix fun [arg](arg.md)(pair: [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;): [NavIntent](index.md)<br>Creates a copy of [NavIntent](index.md) with [pair](arg.md) added to its arguments |
| [asResult](as-result.md) | [androidJvm]<br>fun [asResult](as-result.md)(): [ResolveResult](../-resolve-result/index.md)<br>Converts [NavIntent](index.md) to [ResolveResult.Intent](../-resolve-result/-intent/index.md) |
| [get](get.md) | [androidJvm]<br>operator fun [get](get.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)<br>Returns a value that was previously added via [addArgument](add-argument.md) under the same [key](get.md) |
| [getInt](get-int.md) | [androidJvm]<br>fun [getInt](get-int.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Returns a value that was previously added via [addArgument](add-argument.md) under the same [key](get-int.md) |
| [getString](get-string.md) | [androidJvm]<br>fun [getString](get-string.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns a value that was previously added via [addArgument](add-argument.md) under the same [key](get-string.md) |
| [plus](plus.md) | [androidJvm]<br>operator fun [plus](plus.md)(pair: [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;): [NavIntent](index.md)<br>Creates a copy of [NavIntent](index.md) with [pair](plus.md) added to its arguments |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
