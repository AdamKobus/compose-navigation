package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.action.NavigateAction
import com.adamkobus.compose.navigation.action.PopAction
import com.adamkobus.compose.navigation.data.NavGraph

data class PopDestination(
    override val graph: NavGraph,
    override val route: NavRoute = navRoute(graph.name, path = "__back__", reservedNamesCheck = false)
) : INavDestination {

    override fun next(init: NavRoute.Builder.() -> Unit): NavDestination {
        throw UnsupportedOperationException()
    }

    override fun goTo(other: INavDestination): NavigateAction {
        throw UnsupportedOperationException()
    }

    override fun pop(other: PopDestination): PopAction {
        throw UnsupportedOperationException()
    }

    override fun toString(): String {
        return route.buildRoute()
    }
}

internal fun popDestination(graph: NavGraph): PopDestination = PopDestination(graph = graph)
