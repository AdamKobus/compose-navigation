package com.adamkobus.compose.navigation.model

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationId
import com.adamkobus.compose.navigation.NavigationStateSource
import com.adamkobus.compose.navigation.destination.NavControllerState
import com.adamkobus.compose.navigation.destination.NavState
import com.adamkobus.compose.navigation.logger.NavLogger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * This manager tracks any visited destination. Based on this, it builds a database of destinations and updates the [navState].
 * It depends on NavComposable to work properly.
 */
internal class NavStateManager : NavigationStateSource {

    private val _navState = MutableStateFlow(NavState(emptyList()))
    override val navState: NavState
        get() = _navState.value

    override fun observeNavState(): StateFlow<NavState> = _navState

    private val logger: NavLogger
        get() = ComposeNavigation.getLogger()

    internal fun onBackStackUpdated(navigationId: NavigationId, entry: NavBackStackEntry?, backQueue: List<NavBackStackEntry>) {
        val backStack = backQueue.mapNotNull {
            if (it.destination.id == 0 && it.destination.route == null) {
                // this is the app's root graph that is not part of the navigation tree
                null
            } else {
                it.toNavStackEntry()
            }
        }
        updateCurrentDestination(NavControllerState(navigationId, entry?.toNavStackEntry(), backStack))
    }

    private fun updateCurrentDestination(controllerState: NavControllerState) {
        val existing = _navState.value.get(controllerState.navId)
        if (existing == controllerState) {
            // same state, no change needed
            return
        }
        logger.d("[${controllerState.navId}] Current destination changed to $controllerState")
        if (existing != null) {
            _navState.value = NavState(
                _navState.value.controllersState.map {
                    if (it.navId == controllerState.navId) {
                        controllerState
                    } else {
                        it
                    }
                }
            )
        } else {
            _navState.value = NavState(_navState.value.controllersState + controllerState)
        }
    }
}
