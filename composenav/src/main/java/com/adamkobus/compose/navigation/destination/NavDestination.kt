package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.action.NavigateAction
import com.adamkobus.compose.navigation.action.PopAction
import com.adamkobus.compose.navigation.intent.NavIntent
import com.adamkobus.compose.navigation.intent.navIntent

/**
 * Represents a destination that can be added to back stack
 */
interface NavDestination : INavDestination {

    /**
     * Creates [PopDestination] with this [NavDestination] destination's [graph]
     */
    fun popDestination(): PopDestination =
        popDestination(this.graph)

    /**
     * Creates [PopAction] that uses this [NavDestination] as a source destination
     * and uses this destination's graph in target [PopDestination]
     */
    fun pop(): PopAction =
        PopAction(this, popDestination())

    /**
     * Creates [PopAction] that originates from this [NavDestination] and targets [other].
     *
     * @param other will be used as target destination of created [PopAction]
     */
    infix fun pop(other: PopDestination): PopAction = PopAction(this, other)

    /**
     * Creates [NavigateAction] that originates from this [NavDestination] and targets [other].
     *
     * @param other will be used as target destination of created [NavigateAction]
     */
    infix fun goTo(other: NavDestination) = NavigateAction(this, other)

    /**
     * Creates new [NavIntent]. It will be initialized with provided name and this destination as [NavIntent.origin]
     */
    fun navIntent(name: String): NavIntent =
        navIntent(name, this)
}
