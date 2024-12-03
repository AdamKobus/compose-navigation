package com.adamkobus.compose.navigation.intent

import com.adamkobus.compose.navigation.error.NavIntentCycleDetectedError
import com.adamkobus.compose.navigation.error.NavIntentMappingTooDeepError

internal class NavIntentHistory(startingIntent: NavIntent) {
    private val nodes = mutableListOf(startingIntent)

    /**
     * Adds a node to the tracked graph
     * @param toIntent Intent that was produced by one of the [com.adamkobus.compose.navigation.NavIntentResolver]s
     *
     * @throws [com.adamkobus.compose.navigation.error.NavIntentCycleDetectedError]
     * When adding [toIntent] creates a cycle.
     */
    fun addNode(toIntent: NavIntent) {
        val existingNode = nodes.find { it.name == toIntent.name }
        nodes.add(toIntent)
        if (existingNode != null) {
            throw NavIntentCycleDetectedError(nodes.joinToString(separator = " -> ") { it.toString() })
        }
        if (nodes.size > ITEMS_LIMIT) {
            throw NavIntentMappingTooDeepError("Exceeded limit of NavIntent mappings ($DEPTH_LIMIT). First intent was: $nodes[0]")
        }
    }

    fun popNode(intent: NavIntent) {
        if (nodes.last() == intent) {
            nodes.removeAt(nodes.lastIndex)
        } else {
            throw IllegalStateException(
                "Last node is different from the one that should be removed. " +
                    "Current last: ${nodes.last()} | node to remove $intent",
            )
        }
    }

    companion object {
        private const val DEPTH_LIMIT = 50
        private const val ITEMS_LIMIT = DEPTH_LIMIT + 1
    }
}
