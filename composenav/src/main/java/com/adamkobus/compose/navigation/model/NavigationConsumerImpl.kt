package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavActionWrapper
import com.adamkobus.compose.navigation.action.NavigationResult
import com.adamkobus.compose.navigation.intent.NavIntent
import kotlinx.coroutines.CompletableDeferred

internal class NavigationConsumerImpl : NavigationConsumer {

    private val navProcessor
        get() = ComposeNavigation.getNavProcessor()

    override fun offer(action: NavAction) {
        navProcessor.postNavAction(action)
    }

    override fun offer(wrapper: NavActionWrapper) {
        navProcessor.postNavAction(wrapper.action)
    }

    override fun offer(intent: NavIntent) {
        navProcessor.postNavIntent(intent)
    }

    override suspend fun offerBlocking(action: NavAction): NavigationResult {
        val result = CompletableDeferred<NavigationResult>()
        navProcessor.postNavAction(action, result)
        return result.await()
    }

    override suspend fun offerBlocking(wrapper: NavActionWrapper): NavigationResult = offerBlocking(wrapper.action)

    override suspend fun offerBlocking(intent: NavIntent): NavigationResult {
        val result = CompletableDeferred<NavigationResult>()
        navProcessor.postNavIntent(intent, result)
        return result.await()
    }
}
