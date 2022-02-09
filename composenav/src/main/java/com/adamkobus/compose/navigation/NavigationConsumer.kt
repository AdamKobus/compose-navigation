package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavActionWrapper
import com.adamkobus.compose.navigation.intent.NavIntent

/**
 * Should be used in your ViewModel to dispatch [NavAction]s or [NavIntent]s.
 *
 * There is no guarantee that any of them will result in actual action, as they can be rejected by [NavActionVerifier]
 * or when [NavIntent] could not be resolved by any of the registered [NavIntentResolver]s.
 */
interface NavigationConsumer {
    fun offer(action: NavAction)
    fun offer(action: NavActionWrapper)
    fun offer(intent: NavIntent)
}
