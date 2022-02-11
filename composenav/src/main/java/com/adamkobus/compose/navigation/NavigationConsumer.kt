package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavActionWrapper
import com.adamkobus.compose.navigation.data.NavigationResult
import com.adamkobus.compose.navigation.intent.NavIntent

/**
 * Should be used in your ViewModel to dispatch [NavAction]s or [NavIntent]s.
 *
 * There is no guarantee that any of them will result in actual action, as they can be rejected by [NavActionVerifier]
 * or when [NavIntent] could not be resolved by any of the registered [NavIntentResolver]s.
 */
interface NavigationConsumer {
    /**
     * Will offer the [action] to NavHostController if it's not rejected by any [NavActionVerifier].
     **/
    fun offer(action: NavAction)

    /**
     * Will offer the embedded action to NavHostController if it's not rejected by any [NavActionVerifier].
     **/
    fun offer(action: NavActionWrapper)

    /**
     * Will offer the action resolved by [NavIntentResolver] to NavHostController if it's not rejected by any [NavActionVerifier].
     **/
    fun offer(intent: NavIntent)

    /**
     * Will offer the [action] to NavHostController if it's not rejected by any [NavActionVerifier].
     * This call will suspend current coroutine until navigation or timeout happens.
     *
     * @see [NavigationResult]
     */
    suspend fun offerBlocking(action: NavAction): NavigationResult

    /**
     * Will offer the embedded action to NavHostController if it's not rejected by any [NavActionVerifier].
     * This call will suspend current coroutine until navigation or timeout happens.
     *
     * @see [NavigationResult]
     **/
    suspend fun offerBlocking(action: NavActionWrapper): NavigationResult

    /**
     * Will offer the action resolved by [NavIntentResolver] to NavHostController if it's not rejected by any [NavActionVerifier].
     * This call will suspend current coroutine until navigation or timeout happens.
     *
     * @see [NavigationResult]
     **/
    suspend fun offerBlocking(intent: NavIntent): NavigationResult
}
