package com.adamkobus.compose.navigation.model

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.action.NavAction
import kotlinx.coroutines.flow.Flow

internal interface NavDelegate {
    fun onActionCompletedWithoutBackStackUpdate(actionConsumer: ActionConsumer)

    fun onBackStackEntryUpdated(
        actionConsumer: ActionConsumer,
        entry: NavBackStackEntry?,
        backQueue: List<NavBackStackEntry>,
    )

    fun register(
        actionConsumer: ActionConsumer,
        supportedGraphsRoutes: List<String>,
    ): Flow<NavAction>

    fun unregister(actionConsumer: ActionConsumer)

    fun onBackStackCleared(actionConsumer: ActionConsumer)
}
