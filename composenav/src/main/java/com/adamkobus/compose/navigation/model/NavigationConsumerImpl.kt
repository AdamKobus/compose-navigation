package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavActionWrapper
import com.adamkobus.compose.navigation.data.NavigationResult
import com.adamkobus.compose.navigation.intent.NavIntent
import kotlinx.coroutines.CompletableDeferred

internal class NavigationConsumerImpl : NavigationConsumer {

    private val navProcessor
        get() = ComposeNavigation.getNavigationProcessor()

    override fun offer(action: NavAction) {
        navProcessor.postNavAction(action)
    }

    override fun offer(action: NavActionWrapper) {
        navProcessor.postNavAction(action.action)
    }

    override fun offer(intent: NavIntent) {
        navProcessor.postNavIntent(intent)
    }

    override suspend fun offerBlocking(action: NavAction): NavigationResult {
        val deferred = CompletableDeferred<NavigationResult>()
        navProcessor.postNavAction(action, onTaskCompleted = deferred)
        return deferred.await()
    }

    override suspend fun offerBlocking(action: NavActionWrapper): NavigationResult {
        val deferred = CompletableDeferred<NavigationResult>()
        navProcessor.postNavAction(action.action, onTaskCompleted = deferred)
        return deferred.await()
    }

    override suspend fun offerBlocking(intent: NavIntent): NavigationResult {
        val deferred = CompletableDeferred<NavigationResult>()
        navProcessor.postNavIntent(intent, onTaskCompleted = deferred)
        return deferred.await()
    }
}
