package com.adamkobus.compose.navigation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
internal class NavComposableVM : ViewModel(), ActionConsumer {

    val viewParam = ViewParam<NavComposableParam>()

    private val logger: NavLogger
        get() = ComposeNavigation.getLogger()

    private var loadingCompletable: CompletableDeferred<Boolean>? = CompletableDeferred()

    internal val pendingActionState = mutableStateOf<PendingActionState>(PendingActionState.Missing)

    override var supportedGraphsRoutes: List<String> = emptyList()
    private var navigationId: NavigationId? = null

    private val navigationProcessor: NavigationProcessor?
        get() = navigationId?.let { ComposeNavigation.getNavigationProcessor(it) }

    private val isInitialized = mutableStateOf(false)
    val state = NavComposableState(
        isInitialized = isInitialized
    )

    init {
        viewModelScope.launch {
            try {
                viewParam.observe().flatMapLatest {
                    navigationId = it.navigationId
                    updateGraphRoutes(it.graphs)
                    ComposeNavigation.getNavigationProcessor(it.navigationId).register(this@NavComposableVM).also {
                        isInitialized.value = true
                        loadingCompletable?.complete(true)
                        loadingCompletable = null
                    }
                }.collect {
                    processAction(it)
                }
            } finally {
                navigationId?.let {
                    ComposeNavigation.getNavigationProcessor(it).unregister(this@NavComposableVM)
                }
                isInitialized.value = false
                loadingCompletable?.complete(false)
                loadingCompletable = null
            }
        }
    }

    private fun updateGraphRoutes(routes: List<String>) {
        if (routes != supportedGraphsRoutes) {
            logger.v("$this Updating with graphs $routes")
            supportedGraphsRoutes = routes
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

    override fun toString(): String = "[$navigationId] NavComposable"
}

internal data class NavComposableParam(
    val navigationId: NavigationId,
    val graphs: List<String>
)
