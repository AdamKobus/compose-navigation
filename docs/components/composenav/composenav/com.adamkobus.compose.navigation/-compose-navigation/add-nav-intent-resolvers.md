//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[ComposeNavigation](index.md)/[addNavIntentResolvers](add-nav-intent-resolvers.md)

# addNavIntentResolvers

[androidJvm]\
fun [addNavIntentResolvers](add-nav-intent-resolvers.md)(vararg resolvers: [NavIntentResolver](../-nav-intent-resolver/index.md)): [ComposeNavigation](index.md)

fun [addNavIntentResolvers](add-nav-intent-resolvers.md)(resolvers: [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;[NavIntentResolver](../-nav-intent-resolver/index.md)&gt;): [ComposeNavigation](index.md)

Adds provided [resolvers](add-nav-intent-resolvers.md) so that they can participate in [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)s processing. The resolvers are used in the order in which you're adding them
