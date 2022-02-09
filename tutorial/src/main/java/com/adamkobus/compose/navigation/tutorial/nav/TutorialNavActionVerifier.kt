package com.adamkobus.compose.navigation.tutorial.nav

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.VerifyResult
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.data.GlobalGraph
import com.adamkobus.compose.navigation.destination.CurrentDestination

object TutorialNavActionVerifier : NavActionVerifier {
    override fun isNavActionAllowed(currentDestination: CurrentDestination, action: NavAction): VerifyResult {
        if (action.fromDestination.graph == GlobalGraph) return VerifyResult.Allow
        return if (currentDestination.destination == action.fromDestination) {
            VerifyResult.Allow
        } else {
            VerifyResult.Discard
        }
    }
}
