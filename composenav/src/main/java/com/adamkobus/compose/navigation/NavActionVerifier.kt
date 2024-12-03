package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.destination.NavState

/**
 * Purpose of this class is to verify if [NavAction] execution is allowed based on the application state.
 *
 * As an example, it could be used to prevent duplicate nav action if user clicks list item twice.
 */
interface NavActionVerifier {
    /**
     * @param navState Represents the state of the back stack at the moment of performing this check
     * @param action Action that was produced via [NavigationConsumer.offer]
     *
     * @return [VerifyResult.Discard] if [action] should be discarded.
     * [VerifyResult.Allow] should be returned if this [NavActionVerifier] doesn't want to block the [action]
     *
     * @see [NavState]
     * @see [NavAction]
     */
    fun isNavActionAllowed(
        navState: NavState,
        action: NavAction,
    ): VerifyResult
}
