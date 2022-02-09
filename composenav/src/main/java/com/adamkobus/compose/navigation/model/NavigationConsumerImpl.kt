package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavActionWrapper
import com.adamkobus.compose.navigation.intent.NavIntent
import javax.inject.Inject

internal class NavigationConsumerImpl @Inject constructor(
    private val navProcessor: NavigationProcessor
) : NavigationConsumer {
    override fun offer(action: NavAction) {
        navProcessor.postNavAction(action)
    }

    override fun offer(action: NavActionWrapper) {
        navProcessor.postNavAction(action.action)
    }

    override fun offer(intent: NavIntent) {
        navProcessor.postNavIntent(intent)
    }
}
