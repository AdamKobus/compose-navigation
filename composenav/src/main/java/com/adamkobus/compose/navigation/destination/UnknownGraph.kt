package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.NavigationStateSource

/**
 * Any destination that is not recognized by [NavigationStateSource] is assigned to this graph.
 */
object UnknownGraph : NavGraph("__unknown__", reservedNameCheck = false) {
    /**
     * [UnknownGraph] has fixed startDestination with the same name as the name of the graph.
     */
    override fun startDestination(): NavDestination = screenDestination(name)
}
