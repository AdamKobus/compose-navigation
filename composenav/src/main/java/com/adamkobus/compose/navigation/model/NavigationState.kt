package com.adamkobus.compose.navigation.model

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.data.GlobalGraph
import com.adamkobus.compose.navigation.data.INavDestination
import com.adamkobus.compose.navigation.data.NavAction
import com.adamkobus.compose.navigation.data.NavActionWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NavigationState @Inject constructor(
    private val navGatekeeper: NavGatekeeper
) {
    private val pendingActions = MutableStateFlow<List<NavAction>>(emptyList())

    private val knownDestinations = mutableMapOf<String, INavDestination>()

    private var _currentDestination = MutableStateFlow<INavDestination?>(null)
    val currentDestination: Flow<INavDestination?> = _currentDestination

    fun postNavAction(navActionWrapper: NavActionWrapper) {
        postNavAction(navActionWrapper.action)
    }

    fun postNavAction(navAction: NavAction) {
        synchronized(pendingActions) {
            pendingActions.value = pendingActions.value + navAction
        }
    }

    private fun consume(): NavAction? {
        var ret: NavAction?
        synchronized(pendingActions) {
            ret = getNextNavAction()
        }
        ret?.let { action ->
            if (action.fromDestination.graph != GlobalGraph) {
                knownDestinations[action.fromDestination.route.buildRoute()] = action.fromDestination
            }
            if (action.toDestination.graph != GlobalGraph) {
                knownDestinations[action.toDestination.route.buildRoute()] = action.toDestination
                _currentDestination.value = action.toDestination
            }
        }
        return ret
    }

    private fun consumeAllActions(): List<NavAction> {
        val newActions = mutableListOf<NavAction>()
        var action: NavAction?
        while (
            run {
                action = consume()
                action
            } != null
        ) {
            newActions.add(action!!)
        }
        return newActions
    }

    private fun getNextNavAction(): NavAction? {
        val actionsList = pendingActions.value.toMutableList()
        var ret: NavAction? = null
        while (actionsList.isNotEmpty()) {
            val action = actionsList.removeAt(0)
            val currentDest = _currentDestination.value
            if (currentDest == null || navGatekeeper.isNavActionAllowed(currentDest, action)) {
                ret = action
                break
            }
        }
        pendingActions.value = actionsList
        return ret
    }

    fun observePendingActions(): Flow<List<NavAction>> =
        pendingActions.map {
            if (it.isEmpty()) {
                emptyList()
            } else {
                consumeAllActions()
            }
        }

    fun onBackStackEntryUpdated(entry: NavBackStackEntry?) {
        _currentDestination.value = entry?.destination?.route?.let {
            knownDestinations[it]
        }
    }
}
