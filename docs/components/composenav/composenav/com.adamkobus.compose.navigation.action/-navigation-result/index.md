//[composenav](../../../index.md)/[com.adamkobus.compose.navigation.action](../index.md)/[NavigationResult](index.md)

# NavigationResult

[androidJvm]\
sealed class [NavigationResult](index.md)

Used to indicate a result of [NavAction](../-nav-action/index.md) or [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md) processing.

## Types

| Name | Summary |
|---|---|
| [Accepted](-accepted/index.md) | [androidJvm]<br>object [Accepted](-accepted/index.md) : [NavigationResult](index.md)<br>Indicates that navigation action was executed. |
| [Discarded](-discarded/index.md) | [androidJvm]<br>class [Discarded](-discarded/index.md)(reason: [DiscardReason](../-discard-reason/index.md)) : [NavigationResult](index.md)<br>Indicates that action was discarded with a [reason](-discarded/reason.md) |

## Inheritors

| Name |
|---|
| [Accepted](-accepted/index.md) |
| [Discarded](-discarded/index.md) |
