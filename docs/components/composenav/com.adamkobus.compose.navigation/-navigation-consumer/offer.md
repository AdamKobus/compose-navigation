//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[NavigationConsumer](index.md)/[offer](offer.md)

# offer

[androidJvm]\
abstract fun [offer](offer.md)(action: [NavAction](../../com.adamkobus.compose.navigation.action/-nav-action/index.md))

Will offer the [action](offer.md) to NavHostController if it's not rejected by any [NavActionVerifier](../-nav-action-verifier/index.md).

[androidJvm]\
abstract fun [offer](offer.md)(action: [NavActionWrapper](../../com.adamkobus.compose.navigation.action/-nav-action-wrapper/index.md))

Will offer the embedded action to NavHostController if it's not rejected by any [NavActionVerifier](../-nav-action-verifier/index.md).

[androidJvm]\
abstract fun [offer](offer.md)(intent: [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md))

Will offer the action resolved by [NavIntentResolver](../-nav-intent-resolver/index.md) to NavHostController if it's not rejected by any [NavActionVerifier](../-nav-action-verifier/index.md).
