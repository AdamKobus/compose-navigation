package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavigateAction
import com.adamkobus.compose.navigation.destination.INavDestination

/**
 * Purpose of this class is to verify if [NavigateAction] execution is allowed based on the application state.
 *
 * As an example, it could be used to prevent duplicate nav action if user clicks list item twice.
 *
 * [NavActionVerifier]s should be provided via [dagger.multibindings.IntoSet] bindings.
 */
interface NavActionVerifier {

    /**
     * @param currentDestination Represents the destination displayed to the user at the moment of performing this check
     * @param action Action that was produced via [NavigationState.postNavAction]
     *
     * @return [VerifyResult.Discard] if [action] should be discarded.
     * [VerifyResult.Allow] should be returned if this [NavActionVerifier] doesn't want to block the [action]
     */
    fun isNavActionAllowed(currentDestination: INavDestination, action: NavAction): VerifyResult
}

enum class VerifyResult {
    Allow,
    Discard
}
