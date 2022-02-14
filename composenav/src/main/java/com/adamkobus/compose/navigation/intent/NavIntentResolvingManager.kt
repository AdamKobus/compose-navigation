package com.adamkobus.compose.navigation.intent

import com.adamkobus.compose.navigation.NavIntentResolver
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.destination.NavState

internal class NavIntentResolvingManager {

    private val resolvers = mutableListOf<NavIntentResolver>()

    fun register(resolvers: Collection<NavIntentResolver>) {
        this.resolvers.addAll(resolvers)
    }

    suspend fun resolve(intent: NavIntent, navState: NavState): NavAction? {
        val history = NavIntentHistory(intent)
        val result = getNextResult(intent, navState, history)
        return if (result is ResolveResult.Action) {
            result.action
        } else {
            null
        }
    }

    private suspend fun getNextResult(intent: NavIntent, navState: NavState, history: NavIntentHistory): ResolveResult {
        resolvers.forEach {
            val resolverResult = it.resolve(intent, navState)
            if (resolverResult is ResolveResult.Action) {
                return resolverResult
            }
            if (resolverResult is ResolveResult.Intent) {
                history.addNode(resolverResult.intent)
                val next = getNextResult(resolverResult.intent, navState, history)
                history.popNode(resolverResult.intent)
                if (next is ResolveResult.Action) {
                    return next
                }
            }
        }
        return ResolveResult.None
    }

    internal fun reset() {
        resolvers.clear()
    }
}
