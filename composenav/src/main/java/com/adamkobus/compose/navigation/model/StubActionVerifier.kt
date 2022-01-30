package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.data.INavDestination
import com.adamkobus.compose.navigation.data.NavAction
import javax.inject.Inject

internal class StubActionVerifier @Inject constructor() : NavActionVerifier {

    override fun isNavActionAllowed(currentDestination: INavDestination, action: NavAction): Boolean = true
}
