package com.adamkobus.compose.navigation

import com.adamkobus.compose.navigation.ui.NavComposable

/**
 * Uniquely identifies [ComposeNavHost] or [NavComposable] across the application.
 * Use this ID to query information that is related to those components,
 * i.e. by providing it as param to [ComposeNavigation.getNavigationStateSource].
 *
 * Using same [NavigationId] with multiple components can lead to crashes or unexpected behaviour.
 *
 * @param id value of this field will be used to identify [ComposeNavHost] or [NavComposable]
 */
data class NavigationId(
    val id: String
) {
    /**
     * Returns string representation of [NavigationId]
     */
    override fun toString() = id

    companion object {
        /**
         * This instance of [NavigationId] has no special meaning. It's initialized with "0" value.
         */
        val DEFAULT = NavigationId("0")
    }
}
