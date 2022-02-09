package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.data.ResolveResult
import com.adamkobus.compose.navigation.destination.CurrentDestination
import com.adamkobus.compose.navigation.intent.NavIntent

interface NavIntentResolver {
    /**
     * This method should return one of the availabel results in [ResolveResult]
     * - [ResolveResult.Action] - this will finalize processing of the initial intent and
     * Compose Navigation will move to the next step of navigation handling from this point.
     * There is no guarantee that returned action will be actually processed, because it can be rejected by one of tne [NavActionVerifier]s
     *
     * - [ResolveResult.Intent] - it's perfectly fine to return different intent as a results
     * (although side-effect might be lots of head-scratching by other developers).
     * Returned intent will be resolved further until the library receives [ResolveResult.Action]
     * or it fails to resolve the intent chain into actual action.
     * One thing to keep in mind is that it's possible to create a cycle when doing this.
     * For example, when intent1 is resolved to intent2 which is resolved to intent1 again.
     * Compose Navigation library will detect such situation and throw [com.adamkobus.compose.navigation.error.NavIntentCycleDetectedError].
     *
     * - [ResolveResult.None] - should be returned when the implementation of [NavIntentResolver] doesn't recognize provided [intent]
     */
    fun resolve(intent: NavIntent, currentDestination: CurrentDestination): ResolveResult
}
