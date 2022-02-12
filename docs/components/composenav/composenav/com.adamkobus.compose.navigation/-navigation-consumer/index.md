//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[NavigationConsumer](index.md)

# NavigationConsumer

[androidJvm]\
interface [NavigationConsumer](index.md)

Should be used in your ViewModel to dispatch [NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md)s or [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)s.

There is no guarantee that any of them will result in actual action, as they can be rejected by [NavActionVerifier](../-nav-action-verifier/index.md) or when [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md) could not be resolved by any of the registered [NavIntentResolver](../-nav-intent-resolver/index.md)s.

## Functions

| Name | Summary |
|---|---|
| [offer](offer.md) | [androidJvm]<br>abstract fun [offer](offer.md)(action: [NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md))<br>Will offer the [action](offer.md) to NavHostController if it's not rejected by any [NavActionVerifier](../-nav-action-verifier/index.md).<br>[androidJvm]<br>abstract fun [offer](offer.md)(action: [NavActionWrapper](../../com.adamkobus.compose.navigation.action/-nav-action-wrapper/index.md))<br>Will offer the embedded action to NavHostController if it's not rejected by any [NavActionVerifier](../-nav-action-verifier/index.md).<br>[androidJvm]<br>abstract fun [offer](offer.md)(intent: [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md))<br>Will offer the action resolved by [NavIntentResolver](../-nav-intent-resolver/index.md) to NavHostController if it's not rejected by any [NavActionVerifier](../-nav-action-verifier/index.md). |
| [offerBlocking](offer-blocking.md) | [androidJvm]<br>abstract suspend fun [offerBlocking](offer-blocking.md)(action: [NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md)): [NavigationResult](../../com.adamkobus.compose.navigation.data/-navigation-result/index.md)<br>Will offer the [action](offer-blocking.md) to NavHostController if it's not rejected by any [NavActionVerifier](../-nav-action-verifier/index.md). This call will suspend current coroutine until navigation or timeout happens.<br>[androidJvm]<br>abstract suspend fun [offerBlocking](offer-blocking.md)(action: [NavActionWrapper](../../com.adamkobus.compose.navigation.action/-nav-action-wrapper/index.md)): [NavigationResult](../../com.adamkobus.compose.navigation.data/-navigation-result/index.md)<br>Will offer the embedded action to NavHostController if it's not rejected by any [NavActionVerifier](../-nav-action-verifier/index.md). This call will suspend current coroutine until navigation or timeout happens.<br>[androidJvm]<br>abstract suspend fun [offerBlocking](offer-blocking.md)(intent: [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md)): [NavigationResult](../../com.adamkobus.compose.navigation.data/-navigation-result/index.md)<br>Will offer the action resolved by [NavIntentResolver](../-nav-intent-resolver/index.md) to NavHostController if it's not rejected by any [NavActionVerifier](../-nav-action-verifier/index.md). This call will suspend current coroutine until navigation or timeout happens. |
