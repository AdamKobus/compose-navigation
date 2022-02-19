package com.adamkobus.compose.navigation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavBackStackEntry
import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.android.vm.ViewParam
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.logger.NavLogger
import com.adamkobus.compose.navigation.model.ActionConsumer
import com.adamkobus.compose.navigation.model.NavigationProcessor
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
internal class NavComposableVM : LifecycleAwareViewModel(), ActionConsumer {

    private val id = NavComposableId.next()

    private val navigationProcessor: NavigationProcessor
        get() = ComposeNavigation.getNavigationProcessor()

    private val logger: NavLogger
        get() = ComposeNavigation.getLogger()

    private var loadingCompletable: CompletableDeferred<Boolean>? = CompletableDeferred()
    override var supportedGraphsRoutes: List<String> = emptyList()

    val graphsRoutesParam = ViewParam<List<String>>()
    internal val pendingActionState = mutableStateOf<PendingActionState>(PendingActionState.Missing)

    init {
        runOnStartStop {
            onStart = {
                navigationProcessor.register(this@NavComposableVM).collect {
                    processAction(it)
                }
            }
            onStop = {
                navigationProcessor.unregister(this@NavComposableVM)
            }
        }
        runOnCreateDestroy {
            onCreate = {
                loadingCompletable = CompletableDeferred()
                graphsRoutesParam.collect {
                    updateGraphRoutes(it)
                    loadingCompletable?.complete(true)
                    loadingCompletable = null
                }
            }
            onDestroy = {
                loadingCompletable?.complete(false)
                loadingCompletable = null
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        navigationProcessor.unregister(this@NavComposableVM)
        loadingCompletable?.complete(false)
        loadingCompletable = null
    }

    private fun updateGraphRoutes(routes: List<String>) {
        if (routes != supportedGraphsRoutes) {
            supportedGraphsRoutes = routes
            logger.v("$this Updated tracked graphs to $routes")
        }
    }

    private suspend fun processAction(action: NavAction) {
        val completable = CompletableDeferred<Boolean>()
        pendingActionState.value = PendingActionState.Present(action, completable)
        val backStackModified = completable.await()
        if (!backStackModified) {
            navigationProcessor.onActionCompletedWithoutBackStackUpdate()
        }
        pendingActionState.value = PendingActionState.Missing
    }

    fun processBackStackEntry(entry: NavBackStackEntry?, backQueue: List<NavBackStackEntry>) {
        navigationProcessor.onBackStackEntryUpdated(entry, backQueue)
    }

    override suspend fun awaitUntilReady() {
        loadingCompletable?.await()
    }

    override fun equals(other: Any?): Boolean {
        return other is NavComposableVM && other.id == id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String = "NavComposable[$id]"
}
