package com.adamkobus.compose.navigation.intent

import com.adamkobus.compose.navigation.NavIntentResolver
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.data.ResolveResult
import com.adamkobus.compose.navigation.destination.CurrentDestination

internal class NavIntentResolvingManager {

    private val resolvers = mutableListOf<NavIntentResolver>()

    fun register(resolvers: Collection<NavIntentResolver>) {
        this.resolvers.addAll(resolvers)
    }

    suspend fun resolve(intent: NavIntent, currentDestination: CurrentDestination): NavAction? {
        val history = NavIntentHistory(intent)
        val result = getNextResult(intent, currentDestination, history)
        return if (result is ResolveResult.Action) {
            result.action
        } else {
            null
        }
    }

    private suspend fun getNextResult(intent: NavIntent, currentDestination: CurrentDestination, history: NavIntentHistory): ResolveResult {
        resolvers.forEach {
            val resolverResult = it.resolve(intent, currentDestination)
            if (resolverResult is ResolveResult.Action) {
                return resolverResult
            }
            if (resolverResult is ResolveResult.Intent) {
                history.addNode(resolverResult.intent)
                val next = getNextResult(resolverResult.intent, currentDestination, history)
                history.popNode(resolverResult.intent)
                if (next is ResolveResult.Action) {
                    return next
                }
            }
        }
        return ResolveResult.None
    }
}
