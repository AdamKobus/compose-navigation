package com.adamkobus.compose.navigation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavBackStackEntry
import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.android.vm.ViewParam
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.model.NavigationProcessor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
internal class NavComposableVM @Inject constructor(
    private val navigationProcessor: NavigationProcessor
) : LifecycleAwareViewModel() {

    val graphsParam = ViewParam<List<NavGraph>>()
    val pendingActionState = mutableStateOf<PendingActionState>(PendingActionState.Missing)

    init {
        runOnCreateDestroy {
            onCreate = {
                graphsParam.observe().flatMapLatest {
                    register(it)
                }.collect {
                    val completable = CompletableDeferred<Unit>()
                    pendingActionState.value = PendingActionState.Present(it, completable)
                    completable.await()
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

    fun processBackStackEntry(entry: NavBackStackEntry?) {
        navigationProcessor.onBackStackEntryUpdated(entry)
    }
}
