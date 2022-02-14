package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.VerifyResult
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.destination.NavState

internal class NavGatekeeper(
    private val initialVerifiers: Set<NavActionVerifier> = emptySet()
) {

    private val verifiers = mutableListOf<NavActionVerifier>().apply {
        addAll(initialVerifiers)
    }

    fun isNavActionAllowed(navState: NavState, action: NavAction): NavActionVerifier? {
        synchronized(verifiers) {
            verifiers.forEach {
                if (it.isNavActionAllowed(navState, action) == VerifyResult.Discard) {
                    return it
                }
            }
        }
        return null
    }

    fun addVerifier(verifier: NavActionVerifier) {
        synchronized(verifiers) {
            verifiers.add(verifier)
        }
    }

    fun reset() {
        verifiers.clear()
    }
}
