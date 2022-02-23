//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.intent](../index.md)/[NavIntent](index.md)/[NavIntent](-nav-intent.md)

# NavIntent

[androidJvm]\
fun [NavIntent](-nav-intent.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), origin: [NavDestination](../../com.adamkobus.compose.navigation.destination/-nav-destination/index.md)? = null, popOptions: [NavOptions](../../com.adamkobus.compose.navigation.action/-nav-options/index.md)? = null, navigationId: [NavigationId](../../com.adamkobus.compose.navigation/-navigation-id/index.md)? = null, arguments: [MutableMap](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; = mutableMapOf())

## Parameters

androidJvm

| | |
|---|---|
| name | Unique id of the intent |
| origin | to help [NavIntentResolver](../../com.adamkobus.compose.navigation/-nav-intent-resolver/index.md)s process this NavIntent, you can provide a destination that requested it. |
| popOptions | if this intent should close a flow, then you can provide options that should result in doing that. Example could be an intent that launches home screen after successful log in. Such intent would contain popOptions that remove log in graph. |
| navigationId | Might be helpful to some [NavIntentResolver](../../com.adamkobus.compose.navigation/-nav-intent-resolver/index.md)s to create nav action |
| arguments | provides a way to add some extra information to the intent |
