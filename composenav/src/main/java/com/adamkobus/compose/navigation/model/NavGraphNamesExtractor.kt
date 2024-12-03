package com.adamkobus.compose.navigation.model

import androidx.collection.SparseArrayCompat
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

internal object NavGraphNamesExtractor {
    fun extractGraphNames(
        controller: NavHostController,
        startRoute: String,
        init: NavGraphBuilder.() -> Unit,
    ): List<String> {
        val navGraphBuilder = NavGraphBuilder(controller.navigatorProvider, startRoute, null)
        val nodes = navGraphBuilder.apply(init).build().nodes
        val graphNames = mutableListOf<String>()
        addGraphNames(nodes, graphNames)
        return graphNames
    }

    private fun addGraphNames(
        nodes: SparseArrayCompat<NavDestination>,
        graphNames: MutableList<String>,
    ) {
        for (i in 0 until nodes.size()) {
            val node = nodes.get(nodes.keyAt(i))
            node?.route
            if (node is NavGraph) {
                node.route?.let { graphNames.add(it) }
                addGraphNames(node.nodes, graphNames)
            }
        }
    }
}
