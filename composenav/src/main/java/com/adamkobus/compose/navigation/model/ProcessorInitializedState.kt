package com.adamkobus.compose.navigation.model

import kotlinx.coroutines.CompletableDeferred

internal class ProcessorInitializedState {
    private var state: State = State.Unregistered

    private var initializedDeferred: CompletableDeferred<Boolean> = CompletableDeferred()

    fun onRegistered() {
        initializedDeferred = CompletableDeferred()
        state = State.Registered
    }

    fun onInitComplete() {
        initializedDeferred.complete(true)
    }

    fun onUnregistered() {
        state = State.Unregistered
        initializedDeferred.complete(false)
    }

    suspend fun isInitialized(): Boolean {
        if (state == State.Unregistered) {
            return false
        }
        return initializedDeferred.await()
    }

    fun isRegistered(): Boolean = state == State.Registered

    private enum class State {
        Registered,
        Unregistered,
    }
}
