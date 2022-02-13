//[composenav](../../../index.md)/[com.adamkobus.compose.navigation](../index.md)/[NavIntentResolver](index.md)/[resolve](resolve.md)

# resolve

[androidJvm]\
abstract suspend fun [resolve](resolve.md)(intent: [NavIntent](../../com.adamkobus.compose.navigation.intent/-nav-intent/index.md), currentDestination: [CurrentDestination](../../com.adamkobus.compose.navigation.destination/-current-destination/index.md)): [ResolveResult](../../com.adamkobus.compose.navigation.intent/-resolve-result/index.md)

This method should return one of the available results in [ResolveResult](../../com.adamkobus.compose.navigation.intent/-resolve-result/index.md)

- 
   [ResolveResult.Action](../../com.adamkobus.compose.navigation.intent/-resolve-result/-action/index.md) - this will finalize processing of the initial intent and Compose Navigation will move to the next step of navigation handling from this point. There is no guarantee that returned action will be actually processed, because it can be rejected by one of tne [NavActionVerifier](../-nav-action-verifier/index.md)s
- 
   [ResolveResult.Intent](../../com.adamkobus.compose.navigation.intent/-resolve-result/-intent/index.md) - it's perfectly fine to return different intent as a results (although the side-effect might be lots of head-scratching by other developers). Returned intent will be resolved further until the library receives [ResolveResult.Action](../../com.adamkobus.compose.navigation.intent/-resolve-result/-action/index.md) or it fails to resolve the intent chain into actual action. One thing to keep in mind is that it's possible to create a cycle when doing this. For example, when intent1 is resolved to intent2 which is later resolved to intent1 again. Compose Navigation library will detect such situation and throw [NavIntentCycleDetectedError](../../com.adamkobus.compose.navigation.error/-nav-intent-cycle-detected-error/index.md).
- 
   [ResolveResult.None](../../com.adamkobus.compose.navigation.intent/-resolve-result/-none/index.md) - should be returned when the implementation of [NavIntentResolver](index.md) doesn't recognize provided [intent](resolve.md)
