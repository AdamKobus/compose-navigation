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
import com.adamkobus.compose.navigation.model.NavDelegate
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
internal class NavComposableVM : ViewModel(), ActionConsumer {
    val viewParam = ViewParam<NavComposableParam>()

    private val logger: NavLogger
        get() = ComposeNavigation.getLogger()

    private val logPrefix: String
        get() = navigationId?.let { "[$it]" } ?: "[???]"

    private var loadingCompletable: CompletableDeferred<Boolean>? = CompletableDeferred()

    internal val pendingActionState = mutableStateOf<PendingActionState>(PendingActionState.Missing)

    override var supportedGraphsRoutes: List<String> = emptyList()
    private var navigationId: NavigationId? = null

    private val navDelegate: NavDelegate?
        get() = navigationId?.let { ComposeNavigation.getNavDelegate(it) }

    init {
        viewModelScope.launch {
            try {
                viewParam.observe().flatMapLatest {
                    navigationId = it.navigationId
                    supportedGraphsRoutes = it.graphs
                    logger.d("$logPrefix Init started with supported graphs ${it.graphs}")
                    ComposeNavigation
                        .getNavDelegate(it.navigationId)
                        .register(this@NavComposableVM, supportedGraphsRoutes)
                }.collect {
                    processAction(it)
                }
            } finally {
                navDelegate?.apply {
                    onBackStackCleared(this@NavComposableVM)
                    unregister(this@NavComposableVM)
                }
                loadingCompletable?.let {
                    logger.d("$logPrefix Init cancelled")
                    it.complete(false)
                    loadingCompletable = null
                }
            }
        }
    }

    private suspend fun processAction(action: NavAction) {
        val completable = CompletableDeferred<Boolean>()
        pendingActionState.value = PendingActionState.Present(action, completable)
        val backStackModified = completable.await()
        if (!backStackModified) {
            navDelegate?.onActionCompletedWithoutBackStackUpdate(this)
        }
        pendingActionState.value = PendingActionState.Missing
    }

    fun processBackStackEntry(
        entry: NavBackStackEntry?,
        backQueue: List<NavBackStackEntry>,
    ) {
        navDelegate?.onBackStackEntryUpdated(this@NavComposableVM, entry, backQueue)
        if (entry != null) {
            loadingCompletable?.let {
                logger.d("$logPrefix Init completed")
                it.complete(true)
                loadingCompletable = null
            }
        }
    }

    override suspend fun awaitUntilReady() {
        loadingCompletable?.await()
    }

    override fun toString(): String = "[$navigationId] NavComposable"
}

internal data class NavComposableParam(
    val navigationId: NavigationId,
    val graphs: List<String>,
)
