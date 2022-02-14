package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.NavActionVerifier
import com.adamkobus.compose.navigation.action.NavigateAction

/**
 * You can use this graph to create [NavigateAction]s that do not belong to any of your graphs.
 * It might be a bad idea though as it increases chance for your [NavActionVerifier]s to not work properly.
 */
object GlobalGraph : NavGraph(name = "__global__", reservedNameCheck = false) {

    /**
     * Start destination of [GlobalGraph]. Please keep in mind that navigating to GlobalGraph should be avoided
     */
    private val Root = screenDestination("__root__")

    /**
     * Always returns [Root]
     */
    override fun startDestination(): ScreenDestination = Root

    /**
     * Global destination for back navigation.
     */
    internal val Back = popDestination(this)
}
