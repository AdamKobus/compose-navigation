//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[NavIntentResolver](index.md)

# NavIntentResolver

[androidJvm]\
interface [NavIntentResolver](index.md)

Implementations of this interface, that are registered in ComposeNavigation with [ComposeNavigation.addNavIntentResolvers](../-compose-navigation/add-nav-intent-resolvers.md), participate in [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md) resolving.

## See also

androidJvm

| | |
|---|---|
| [com.adamkobus.compose.navigation.NavIntentResolver](resolve.md) |  |

## Functions

| Name | Summary |
|---|---|
| [resolve](resolve.md) | [androidJvm]<br>abstract suspend fun [resolve](resolve.md)(intent: [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md), currentDestination: [CurrentDestination](../../com.adamkobus.compose.navigation.destination/-current-destination/index.md)): [ResolveResult](../../com.adamkobus.compose.navigation.intent/-resolve-result/index.md)<br>This method should return one of the available results in [ResolveResult](../../com.adamkobus.compose.navigation.intent/-resolve-result/index.md) |

## Inheritors

| Name |
|---|
| [TabBarIntentResolver](../-tab-bar-intent-resolver/index.md) |
