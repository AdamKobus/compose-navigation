package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.action.DiscardReason
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavActionWrapper
import com.adamkobus.compose.navigation.action.NavigationResult
import com.adamkobus.compose.navigation.intent.NavIntent
import kotlinx.coroutines.CompletableDeferred

internal class NavigationConsumerImpl : NavigationConsumer {

    private val navProcessors
        get() = ComposeNavigation.getAllNavProcessors()

    override fun offer(action: NavAction) {
        navProcessors.forEach { it.postNavAction(action) }
    }

    override fun offer(wrapper: NavActionWrapper) {
        navProcessors.forEach { it.postNavAction(wrapper.action) }
    }

    override fun offer(intent: NavIntent) {
        navProcessors.forEach { it.postNavIntent(intent) }
    }

    override suspend fun offerBlocking(action: NavAction): NavigationResult {
        val pairs = navProcessors.map {
            Pair(it, CompletableDeferred<NavigationResult>())
        }
        pairs.forEach {
            it.first.postNavAction(action, onTaskCompleted = it.second)
        }
        var result: NavigationResult = NavigationResult.Discarded(DiscardReason.NotDelivered)
        pairs.forEach {
            val innerResult = it.second.await()
            if (result !is NavigationResult.Accepted) {
                result = innerResult
            }
        }
        return result
    }

    override suspend fun offerBlocking(wrapper: NavActionWrapper): NavigationResult = offerBlocking(wrapper.action)

    override suspend fun offerBlocking(intent: NavIntent): NavigationResult {
        val pairs = navProcessors.map {
            Pair(it, CompletableDeferred<NavigationResult>())
        }
        pairs.forEach {
            it.first.postNavIntent(intent, onTaskCompleted = it.second)
        }
        var result: NavigationResult = NavigationResult.Discarded(DiscardReason.NotDelivered)
        pairs.forEach {
            val innerResult = it.second.await()
            if (result !is NavigationResult.Accepted) {
                result = innerResult
            }
        }
        return result
    }
}
