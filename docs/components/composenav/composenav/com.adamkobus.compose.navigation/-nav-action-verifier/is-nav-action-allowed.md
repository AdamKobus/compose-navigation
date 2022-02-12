//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[NavActionVerifier](index.md)/[isNavActionAllowed](is-nav-action-allowed.md)

# isNavActionAllowed

[androidJvm]\
abstract fun [isNavActionAllowed](is-nav-action-allowed.md)(currentDestination: [CurrentDestination](../../com.adamkobus.compose.navigation.destination/-current-destination/index.md), action: [NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md)): [VerifyResult](../-verify-result/index.md)

#### Return

[VerifyResult.Discard](../-verify-result/-discard/index.md) if [action](is-nav-action-allowed.md) should be discarded. [VerifyResult.Allow](../-verify-result/-allow/index.md) should be returned if this [NavActionVerifier](index.md) doesn't want to block the [action](is-nav-action-allowed.md)

## See also

androidJvm

| | |
|---|---|
| [com.adamkobus.compose.navigation.destination.CurrentDestination](../../com.adamkobus.compose.navigation.destination/-current-destination/index.md) |  |
| [com.adamkobus.compose.navigation.action.NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md) |  |

## Parameters

androidJvm

| | |
|---|---|
| currentDestination | Represents the destination displayed to the user at the moment of performing this check |
| action | Action that was produced via [NavigationConsumer.offer](../-navigation-consumer/offer.md) |
