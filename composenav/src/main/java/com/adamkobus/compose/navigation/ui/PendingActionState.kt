package com.adamkobus.compose.navigation.ui

import com.adamkobus.compose.navigation.action.NavAction
import kotlinx.coroutines.CompletableDeferred

internal sealed class PendingActionState {

    object Missing : PendingActionState()

    class Present(val action: NavAction, private val completable: CompletableDeferred<Boolean>) : PendingActionState() {
        override fun equals(other: Any?): Boolean {
            return other is Present && other.action == action
        }

        override fun hashCode(): Int {
            return action.hashCode()
        }

        fun complete(backStackModified: Boolean) {
            completable.complete(backStackModified)
        }
    }
}
