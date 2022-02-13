package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.destination.CurrentDestination
import com.adamkobus.compose.navigation.error.NavIntentCycleDetectedError
import com.adamkobus.compose.navigation.intent.NavIntent
import com.adamkobus.compose.navigation.intent.ResolveResult

/**
 * Implementations of this interface, that are registered in ComposeNavigation with [ComposeNavigation.addNavIntentResolvers],
 * participate in [NavIntent] resolving.
 *
 * @see [NavIntentResolver.resolve]
 */
interface NavIntentResolver {
    /**
     * This method should return one of the available results in [ResolveResult]
     * - [ResolveResult.Action] - this will finalize processing of the initial intent and
     * Compose Navigation will move to the next step of navigation handling from this point.
     * There is no guarantee that returned action will be actually processed, because it can be rejected by one of tne [NavActionVerifier]s
     *
     * - [ResolveResult.Intent] - it's perfectly fine to return different intent as a results
     * (although the side-effect might be lots of head-scratching by other developers).
     * Returned intent will be resolved further until the library receives [ResolveResult.Action]
     * or it fails to resolve the intent chain into actual action.
     * One thing to keep in mind is that it's possible to create a cycle when doing this.
     * For example, when intent1 is resolved to intent2 which is later resolved to intent1 again.
     * Compose Navigation library will detect such situation and throw [NavIntentCycleDetectedError].
     *
     * - [ResolveResult.None] - should be returned when the implementation of [NavIntentResolver] doesn't recognize provided [intent]
     */
    suspend fun resolve(intent: NavIntent, currentDestination: CurrentDestination): ResolveResult
}
