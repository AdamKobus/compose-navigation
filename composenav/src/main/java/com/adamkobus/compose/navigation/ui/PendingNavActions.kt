package com.adamkobus.compose.navigation.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.adamkobus.compose.navigation.data.NavAction

internal class PendingNavActions {
    private val _pendingActions = mutableStateOf<List<NavAction>>(emptyList())
    val pendingActions: State<List<NavAction>> = _pendingActions

    fun consumeActions(): List<NavAction> {
        val ret = _pendingActions.value
        if (ret.isNotEmpty()) {
            _pendingActions.value = emptyList()
        }
        return ret
    }

    fun offerActions(actions: List<NavAction>) {
        if (actions.isNotEmpty()) {
            _pendingActions.value = _pendingActions.value + actions
        }
    }
}
