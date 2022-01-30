package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.data.INavDestination
import com.adamkobus.compose.navigation.data.NavAction

/**
 * Purpose of this class is to verify if [NavAction] execution is allowed based on the application state.
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
     * @return false if [action] should be discarded.
     * True should be returned if [action] can be processed further for given [currentDestination]
     * or if [action] is not recognized by this [NavActionVerifier] implementation.
     */
    fun isNavActionAllowed(currentDestination: INavDestination, action: NavAction): Boolean
}
