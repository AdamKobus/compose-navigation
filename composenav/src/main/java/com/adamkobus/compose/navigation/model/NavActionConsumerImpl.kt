package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.NavActionConsumer
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavActionWrapper
import javax.inject.Inject

internal class NavActionConsumerImpl @Inject constructor(
    private val navProcessor: NavigationProcessor
) : NavActionConsumer {
    override fun offer(action: NavAction) {
        navProcessor.postNavAction(action)
    }

    override fun offer(action: NavActionWrapper) {
        navProcessor.postNavAction(action.action)
    }
}
