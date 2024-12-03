package com.adamkobus.compose.navigation.tutorial.nav

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.VerifyResult
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.destination.GlobalGraph
import com.adamkobus.compose.navigation.destination.NavState

object TutorialNavActionVerifier : NavActionVerifier {
    override fun isNavActionAllowed(
        navState: NavState,
        action: NavAction,
    ): VerifyResult {
        if (action.fromDestination.graph == GlobalGraph) return VerifyResult.Allow
        return if (navState.isCurrent(action.fromDestination)) {
            VerifyResult.Allow
        } else {
            VerifyResult.Discard
        }
    }
}
