package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.VerifyResult
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.destination.CurrentDestination

internal class NavGatekeeper(
    private val initialVerifiers: Set<NavActionVerifier> = emptySet()
) {

    private val verifiers = mutableListOf<NavActionVerifier>().apply {
        addAll(initialVerifiers)
    }

    fun isNavActionAllowed(currentDestination: CurrentDestination, action: NavAction): Boolean {
        synchronized(verifiers) {
            verifiers.forEach {
                if (it.isNavActionAllowed(currentDestination, action) == VerifyResult.Discard) {
                    return false
                }
            }
        }
        return true
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

fun Boolean.asVerifyResult(): VerifyResult =
    if (this) {
        VerifyResult.Allow
    } else {
        VerifyResult.Discard
    }
