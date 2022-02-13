//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.intent](../index.md)/[ResolveResult](index.md)

# ResolveResult

[androidJvm]\
sealed class [ResolveResult](index.md)

Produced as a result of [NavIntent](../-nav-intent/index.md) processing by [NavIntentResolver](../../com.adamkobus.compose.navigation/-nav-intent-resolver/index.md)s

## Types

| Name | Summary |
|---|---|
| [Action](-action/index.md) | [androidJvm]<br>class [Action](-action/index.md)(action: [NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md)) : [ResolveResult](index.md)<br>Indicates that [NavIntent](../-nav-intent/index.md) was resolved to the [action](-action/action.md) |
| [Intent](-intent/index.md) | [androidJvm]<br>class [Intent](-intent/index.md)(intent: [NavIntent](../-nav-intent/index.md)) : [ResolveResult](index.md)<br>Indicates that [NavIntent](../-nav-intent/index.md) was resolved into another [intent](-intent/intent.md) |
| [None](-none/index.md) | [androidJvm]<br>object [None](-none/index.md) : [ResolveResult](index.md)<br>Indicates that [NavIntentResolver](../../com.adamkobus.compose.navigation/-nav-intent-resolver/index.md) did not know how to process provided [NavIntent](../-nav-intent/index.md) |

## Inheritors

| Name |
|---|
| [Action](-action/index.md) |
| [Intent](-intent/index.md) |
| [None](-none/index.md) |
