package com.adamkobus.compose.navigation.model

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.NavigationId
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.logger.NavLogger
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

internal class PendingActionDispatcher(
    internal val navigationId: NavigationId,
    private val mainDispatcher: CoroutineDispatcher,
    private val loggerProvider: Provider<NavLogger>,
    private val timeoutProvider: Provider<Long>,
    private val stateManager: NavStateManager,
) : NavDelegate {
    private val actionDispatchTimeoutMs: Long
        get() = timeoutProvider.provide()

    private val logPrefix = "[$navigationId]"
    private val logger: NavLogger
        get() = loggerProvider.provide()

    private var consumer: PendingActionConsumer? = null
    private var onActionPerformed: CompletableDeferred<DispatchResult>? = null
    private var timeoutJob: Job? = null

    override fun onBackStackEntryUpdated(
        actionConsumer: ActionConsumer,
        entry: NavBackStackEntry?,
        backQueue: List<NavBackStackEntry>,
    ) {
        if (actionConsumer == consumer?.owner) {
            stateManager.onBackStackUpdated(navigationId, entry, backQueue)
            completeAction(DispatchResult.Success)
        }
    }

    override fun onActionCompletedWithoutBackStackUpdate(actionConsumer: ActionConsumer) {
        if (actionConsumer == consumer?.owner) {
            completeAction(DispatchResult.Success)
        }
    }

    override fun onBackStackCleared(actionConsumer: ActionConsumer) {
        if (actionConsumer == consumer?.owner) {
            stateManager.onBackStackUpdated(navigationId, null, emptyList())
            completeAction(DispatchResult.NotDelivered)
        }
    }

    override fun register(
        actionConsumer: ActionConsumer,
        supportedGraphsRoutes: List<String>,
    ): Flow<NavAction> {
        consumer?.let {
            unregister(it.owner)
        }
        logger.v("$logPrefix Registering action consumer: $actionConsumer")
        val flow = MutableSharedFlow<NavAction>()
        consumer =
            PendingActionConsumer(
                actionConsumer,
                flow,
                CoroutineScope(this.mainDispatcher + SupervisorJob()),
                supportedGraphsRoutes,
            )
        return flow
    }

    override fun unregister(actionConsumer: ActionConsumer) {
        consumer?.takeIf {
            it.owner == actionConsumer
        }?.let {
            logger.v("$logPrefix Unregistering action consumer: $actionConsumer")
            it.scope.cancel()
            consumer = null
        }
    }

    private suspend fun getReadyConsumer(): PendingActionConsumer? {
        return consumer?.let {
            it.owner.awaitUntilReady()
            if (it == this.consumer) {
                it
            } else {
                null
            }
        }
    }

    /**
     * Suspends the coroutine until consumer connected with this dispatcher is ready.
     *
     * @return true if this dispatcher is ready and has active consumer.
     * False if consumer is not available and this dispatcher cannot process any actions
     */
    suspend fun awaitUntilReady(): Boolean {
        return getReadyConsumer() != null
    }

    suspend fun dispatchAction(action: NavAction): CompletableDeferred<DispatchResult> =
        consumer?.let {
            dispatchToConsumer(action, it)
        } ?: CompletableDeferred<DispatchResult>().apply {
            complete(DispatchResult.WrongNavHost)
        }

    suspend fun getSupportedGraphRoutes(): List<String>? = getReadyConsumer()?.supportedGraphsRoutes

    private suspend fun dispatchToConsumer(
        action: NavAction,
        consumer: PendingActionConsumer,
    ): CompletableDeferred<DispatchResult> {
        val ret = CompletableDeferred<DispatchResult>()
        onActionPerformed = ret
        var job: Job? = null
        job =
            consumer.scope.launch {
                timeoutJob =
                    consumer.scope.launch {
                        delay(actionDispatchTimeoutMs)
                        job?.cancel()
                        if (ret == onActionPerformed) {
                            completeAction(DispatchResult.Timeout)
                        }
                    }
                consumer.owner.awaitUntilReady()
                consumer.consumerFlow.emit(action)
            }
        return ret
    }

    private fun completeAction(result: DispatchResult) {
        onActionPerformed?.complete(result)
        onActionPerformed = null
        timeoutJob?.cancel()
        timeoutJob = null
    }

    override fun toString(): String {
        return "[$navigationId]"
    }
}

private data class PendingActionConsumer(
    val owner: ActionConsumer,
    val consumerFlow: MutableSharedFlow<NavAction>,
    val scope: CoroutineScope,
    val supportedGraphsRoutes: List<String>,
) {
    override fun toString(): String {
        return owner.toString()
    }
}

internal enum class DispatchResult {
    WrongNavHost,
    NotDelivered,
    Timeout,
    Success,
}
