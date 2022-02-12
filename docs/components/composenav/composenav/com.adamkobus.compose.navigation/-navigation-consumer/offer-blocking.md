//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[NavigationConsumer](index.md)/[offerBlocking](offer-blocking.md)

# offerBlocking

[androidJvm]\
abstract suspend fun [offerBlocking](offer-blocking.md)(action: [NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md)): [NavigationResult](../../com.adamkobus.compose.navigation.data/-navigation-result/index.md)

Will offer the [action](offer-blocking.md) to NavHostController if it's not rejected by any [NavActionVerifier](../-nav-action-verifier/index.md). This call will suspend current coroutine until navigation or timeout happens.

## See also

androidJvm

| | |
|---|---|
| [com.adamkobus.compose.navigation.data.NavigationResult](../../com.adamkobus.compose.navigation.data/-navigation-result/index.md) |  |

[androidJvm]\
abstract suspend fun [offerBlocking](offer-blocking.md)(action: [NavActionWrapper](../../com.adamkobus.compose.navigation.action/-nav-action-wrapper/index.md)): [NavigationResult](../../com.adamkobus.compose.navigation.data/-navigation-result/index.md)

Will offer the embedded action to NavHostController if it's not rejected by any [NavActionVerifier](../-nav-action-verifier/index.md). This call will suspend current coroutine until navigation or timeout happens.

## See also

androidJvm

| | |
|---|---|
| [com.adamkobus.compose.navigation.data.NavigationResult](../../com.adamkobus.compose.navigation.data/-navigation-result/index.md) |  |

[androidJvm]\
abstract suspend fun [offerBlocking](offer-blocking.md)(intent: [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)): [NavigationResult](../../com.adamkobus.compose.navigation.data/-navigation-result/index.md)

Will offer the action resolved by [NavIntentResolver](../-nav-intent-resolver/index.md) to NavHostController if it's not rejected by any [NavActionVerifier](../-nav-action-verifier/index.md). This call will suspend current coroutine until navigation or timeout happens.

## See also

androidJvm

| | |
|---|---|
| [com.adamkobus.compose.navigation.data.NavigationResult](../../com.adamkobus.compose.navigation.data/-navigation-result/index.md) |  |
