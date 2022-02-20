package com.adamkobus.compose.navigation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavBackStackEntry
import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.android.vm.ViewParam
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavigationId
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.logger.NavLogger
import com.adamkobus.compose.navigation.model.ActionConsumer
import com.adamkobus.compose.navigation.model.NavigationProcessor
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class)
internal class NavComposableVM : LifecycleAwareViewModel(), ActionConsumer {

    val viewParam = ViewParam<NavComposableParam>()

    private val logger: NavLogger
        get() = ComposeNavigation.getLogger()

    private var loadingCompletable: CompletableDeferred<Boolean>? = CompletableDeferred()

    internal val pendingActionState = mutableStateOf<PendingActionState>(PendingActionState.Missing)

    override var supportedGraphsRoutes: List<String> = emptyList()
    private var navigationId: NavigationId? = NavigationId.DEFAULT

    private val navigationProcessor: NavigationProcessor?
        get() = navigationId?.let { ComposeNavigation.getNavigationProcessor(it) }

    private val isInitialized = mutableStateOf(false)
    val state = NavComposableState(
        isInitialized = isInitialized
    )

    init {
        runOnCreate {
            try {
                viewParam.observe().flatMapLatest {
                    navigationId = it.navigationId
                    ComposeNavigation.getNavigationProcessor(it.navigationId).register(this@NavComposableVM).also {
                        isInitialized.value = true
                    }
                }.collect {
                    processAction(it)
                }
            } finally {
                unregister()
            }
        }
        runOnCreateDestroy {
            onCreate = {
                loadingCompletable = CompletableDeferred()

                viewParam.collect { param ->
                    navigationId = param.navigationId
                    updateGraphRoutes(param.graphs)
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
        unregister()
        loadingCompletable?.complete(false)
        loadingCompletable = null
    }

    private fun unregister() {
        navigationId?.let {
            ComposeNavigation.getNavigationProcessor(it).unregister(this@NavComposableVM)
        }
        isInitialized.value = false
    }

    private fun updateGraphRoutes(routes: List<String>) {
        if (routes != supportedGraphsRoutes) {
            supportedGraphsRoutes = routes
            logger.v("$this: Updated tracked graphs to $routes")
        }
    }

    private suspend fun processAction(action: NavAction) {
        val completable = CompletableDeferred<Boolean>()
        pendingActionState.value = PendingActionState.Present(action, completable)
        val backStackModified = completable.await()
        if (!backStackModified) {
            navigationProcessor?.onActionCompletedWithoutBackStackUpdate()
        }
        pendingActionState.value = PendingActionState.Missing
    }

    fun processBackStackEntry(entry: NavBackStackEntry?, backQueue: List<NavBackStackEntry>) {
        navigationProcessor?.onBackStackEntryUpdated(entry, backQueue)
    }

    override suspend fun awaitUntilReady() {
        loadingCompletable?.await()
    }

    override fun equals(other: Any?): Boolean {
        return other is NavComposableVM && other.navigationId == navigationId
    }

    override fun hashCode(): Int {
        return navigationId?.hashCode() ?: 0
    }

    override fun toString(): String = "[$navigationId] NavComposable"
}

internal data class NavComposableParam(
    val navigationId: NavigationId,
    val graphs: List<String>
)
