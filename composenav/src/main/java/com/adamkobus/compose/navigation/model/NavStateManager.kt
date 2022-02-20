package com.adamkobus.compose.navigation.model

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationId
import com.adamkobus.compose.navigation.NavigationStateSource
import com.adamkobus.compose.navigation.destination.NavState
import com.adamkobus.compose.navigation.logger.NavLogger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * This manager tracks any visited destination. Based on this, it builds a database of destinations and updates the [navState].
 * It depends on NavComposable to work properly.
 */
internal class NavStateManager(val navigationId: NavigationId) : NavigationStateSource {

    private val _navState = MutableStateFlow(NavState(null, emptyList()))
    override val navState: NavState
        get() = _navState.value

    private val logPrefix = "[$navigationId]"

    override fun observeNavState(): StateFlow<NavState> = _navState

    private val logger: NavLogger
        get() = ComposeNavigation.getLogger()

    internal fun onBackStackUpdated(entry: NavBackStackEntry?, backQueue: List<NavBackStackEntry>) {
        if (entry == null) {
            updateCurrentDestination(NavState(null, emptyList()))
        } else {
            val backStack = backQueue.mapNotNull {
                it.toNavStackEntry()
            }
            updateCurrentDestination(NavState(entry.toNavStackEntry(), backStack))
        }
    }

    private fun updateCurrentDestination(destination: NavState) {
        if (_navState.value != destination) {
            _navState.value = destination
            logger.d("$logPrefix Current destination changed to $destination")
        }
    }
}
