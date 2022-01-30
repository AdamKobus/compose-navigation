package com.adamkobus.compose.navigation.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.adamkobus.compose.navigation.NavActionProcessor
import com.adamkobus.compose.navigation.data.NavAction
import com.adamkobus.compose.navigation.ext.onStartStop
import com.adamkobus.compose.navigation.model.NavigationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NavComposableVM @Inject constructor(
    private val processors: Set<@JvmSuppressWildcards NavActionProcessor>,
    private val navigationStateSource: NavigationState
) : ViewModel(), LifecycleEventObserver {

    private var observeNavActionAvailableJob: Job? = null

    private val pendingNavActions = PendingNavActions()
    val pendingActions = pendingNavActions.pendingActions

    fun processNavActions(navController: NavHostController) {
        viewModelScope.launch {
            pendingNavActions.consumeActions().forEach {
                processAction(it, navController)
            }
        }
    }

    private fun processAction(action: NavAction, navController: NavHostController) {
        processors.forEach { processor ->
            if (processor.process(action, navController)) return
        }
        throw IllegalArgumentException("Unexpected NavAction: $action")
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        event.onStartStop(onStart = this::onStart, onStop = this::onStop)
    }

    private fun onStart() {
        cleanUp()
        observeNavActionAvailableJob = viewModelScope.launch {
            navigationStateSource.observePendingActions().collect {
                pendingNavActions.offerActions(it)
            }
        }
    }

    private fun onStop() {
        cleanUp()
    }

    private fun cleanUp() {
        observeNavActionAvailableJob?.cancel()
        observeNavActionAvailableJob = null
    }

    fun processBackStackEntry(entry: NavBackStackEntry?) {
        navigationStateSource.onBackStackEntryUpdated(entry)
    }
}
