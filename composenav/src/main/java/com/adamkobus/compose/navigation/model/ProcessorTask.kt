package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavigationResult
import com.adamkobus.compose.navigation.intent.NavIntent
import kotlinx.coroutines.CompletableDeferred

internal sealed class ProcessorTask(val onTaskCompleted: CompletableDeferred<NavigationResult>?) {
    class Action(val navAction: NavAction, onTaskCompleted: CompletableDeferred<NavigationResult>?) : ProcessorTask(onTaskCompleted) {
        override fun equals(other: Any?): Boolean {
            return other is Action && other.navAction == navAction
        }

        override fun hashCode(): Int {
            return navAction.hashCode()
        }
    }

    class Intent(val navIntent: NavIntent, onTaskCompleted: CompletableDeferred<NavigationResult>?) : ProcessorTask(onTaskCompleted) {
        override fun equals(other: Any?): Boolean {
            return other is Intent && other.navIntent == navIntent
        }

        override fun hashCode(): Int {
            return navIntent.hashCode()
        }
    }
}
