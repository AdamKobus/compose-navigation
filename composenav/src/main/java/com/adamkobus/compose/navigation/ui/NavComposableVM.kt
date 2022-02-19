package com.adamkobus.compose.navigation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavBackStackEntry
import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.android.vm.ViewParam
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.destination.NavGraph
import com.adamkobus.compose.navigation.model.NavigationProcessor
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class)
internal class NavComposableVM : LifecycleAwareViewModel() {

    private val navigationProcessor: NavigationProcessor
        get() = ComposeNavigation.getNavigationProcessor()

    val graphsParam = ViewParam<List<NavGraph>>()
    val pendingActionState = mutableStateOf<PendingActionState>(PendingActionState.Missing)

    init {
        runOnCreateDestroy {
            onCreate = {
                graphsParam.observe().flatMapLatest {
                    register(it)
                }.collect {
                    val completable = CompletableDeferred<Boolean>()
                    pendingActionState.value = PendingActionState.Present(it, completable)
                    val backStackModified = completable.await()
                    if (!backStackModified) {
                        navigationProcessor.onActionCompletedWithoutBackStackUpdate()
                    }
                    pendingActionState.value = PendingActionState.Missing
                }
            }
            onDestroy = {
                unregister()
            }
        }
    }

    private fun register(graphs: List<NavGraph>): Flow<NavAction> {
        unregister()
        return navigationProcessor.register(this, graphs)
    }

    private fun unregister() {
        navigationProcessor.unregister(this)
    }

    fun processBackStackEntry(entry: NavBackStackEntry?, backQueue: List<NavBackStackEntry>) {
        navigationProcessor.onBackStackEntryUpdated(entry, backQueue)
    }
}
