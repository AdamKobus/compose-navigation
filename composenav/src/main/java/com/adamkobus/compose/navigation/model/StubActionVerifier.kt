package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.VerifyResult
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.destination.CurrentDestination
import javax.inject.Inject

internal class StubActionVerifier @Inject constructor() : NavActionVerifier {

    override fun isNavActionAllowed(currentDestination: CurrentDestination, action: NavAction): VerifyResult = VerifyResult.Allow
}
