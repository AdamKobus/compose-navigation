package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.data.INavDestination
import com.adamkobus.compose.navigation.data.NavAction
import javax.inject.Inject

internal class NavGatekeeper @Inject constructor(
    private val verifiers: Set<@JvmSuppressWildcards NavActionVerifier>
) {
    fun isNavActionAllowed(currentDestination: INavDestination, action: NavAction): Boolean {
        verifiers.forEach {
            if (!it.isNavActionAllowed(currentDestination, action)) {
                return false
            }
        }
        return true
    }
}
