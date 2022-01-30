package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.NavActionConsumer
import com.adamkobus.compose.navigation.data.NavAction
import com.adamkobus.compose.navigation.data.NavActionWrapper
import javax.inject.Inject

internal class NavActionConsumerImpl @Inject constructor(
    private val navState: NavigationState
) : NavActionConsumer {
    override fun offer(action: NavAction) {
        navState.postNavAction(action)
    }

    override fun offer(action: NavActionWrapper) {
        navState.postNavAction(action)
    }
}
