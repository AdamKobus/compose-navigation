package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.logger.NavLogger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext

internal class PendingActionDispatcher {
    private val dispatcher: CoroutineDispatcher
        get() = ComposeNavigation.getMainDispatcher()
    private val consumers = mutableListOf<PendingActionConsumer>()

    private val logger: NavLogger
        get() = ComposeNavigation.getLogger()

    fun register(actionConsumer: ActionConsumer): Flow<NavAction> {
        synchronized(consumers) {
            consumers.find { it.owner == actionConsumer }?.let {
                logger.w("Registering action consumer that is already registered: $actionConsumer")
                return it.consumerFlow
            }
            val flow = MutableSharedFlow<NavAction>()
            consumers.add(0, PendingActionConsumer(actionConsumer, flow, CoroutineScope(dispatcher + SupervisorJob())))
            return flow
        }
    }

    fun unregister(owner: ActionConsumer) {
        synchronized(consumers) {
            consumers.find { it.owner == owner }?.let {
                it.scope.cancel()
                consumers.remove(it)
            }
        }
    }

    suspend fun dispatchAction(action: NavAction): Boolean {
        val localConsumers = synchronized(consumers) {
            consumers.toList()
        }
        localConsumers.forEach {
            it.owner.awaitUntilReady()
        }
        val consumer = synchronized(consumers) {
            consumers.find {
                it.owner.supportedGraphsRoutes.isEmpty() ||
                    it.owner.supportedGraphsRoutes.contains(action.fromDestination.graph.route.buildRoute())
            }
        }
        var ret = false
        consumer?.let {
            withContext(it.scope.coroutineContext) {
                logger.v("Action $action was delivered to ${it.owner}")
                it.consumerFlow.emit(action)
                ret = true
            }
        } ?: run {
            logger.w("Found no NavComposable that could accept $action")
        }
        return ret
    }
}

private data class PendingActionConsumer(
    val owner: ActionConsumer,
    val consumerFlow: MutableSharedFlow<NavAction>,
    val scope: CoroutineScope
)
