package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.VerifyResult
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.destination.CurrentDestination
import javax.inject.Inject

internal class NavGatekeeper @Inject constructor(
    private val verifiers: Set<@JvmSuppressWildcards NavActionVerifier>
) {
    fun isNavActionAllowed(currentDestination: CurrentDestination, action: NavAction): Boolean {
        verifiers.forEach {
            if (it.isNavActionAllowed(currentDestination, action) == VerifyResult.Discard) {
                return false
            }
        }
        return true
    }
}
