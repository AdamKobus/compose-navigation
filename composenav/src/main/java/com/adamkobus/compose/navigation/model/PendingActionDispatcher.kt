package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavigateAction
import com.adamkobus.compose.navigation.action.PopAction
import com.adamkobus.compose.navigation.action.PopUpToAction
import com.adamkobus.compose.navigation.destination.GlobalGraph
import com.adamkobus.compose.navigation.logger.NavLogger
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
    private val mainDispatcher: CoroutineDispatcher,
    private val loggerProvider: Provider<NavLogger>,
    private val timeoutProvider: Provider<Long>
) {
    private val actionDispatchTimeoutMs: Long
        get() = timeoutProvider.provide()

    private val logger: NavLogger
        get() = loggerProvider.provide()

    private var consumer: PendingActionConsumer? = null

    fun register(actionConsumer: ActionConsumer): Flow<NavAction> {
        consumer?.let {
            unregister(it.owner)
        }
        val flow = MutableSharedFlow<NavAction>()
        consumer = PendingActionConsumer(actionConsumer, flow, CoroutineScope(this.mainDispatcher + SupervisorJob()))
        return flow
    }

    fun unregister(actionConsumer: ActionConsumer) {
        consumer?.takeIf {
            it.owner == actionConsumer
        }?.let {
            it.scope.cancel()
            consumer = null
        }
    }

    suspend fun dispatchAction(action: NavAction): DispatchResult =
        getConsumerWithAction(action)?.let {
            dispatchToConsumer(action, it)
        } ?: DispatchResult.WrongNavHost

    private suspend fun dispatchToConsumer(action: NavAction, consumer: PendingActionConsumer): DispatchResult {
        var ret = DispatchResult.NotDelivered
        var job: Job? = null
        job = consumer.scope.launch {
            val timeoutJob: Job?
            timeoutJob = consumer.scope.launch {
                delay(actionDispatchTimeoutMs)
                job?.cancel()
                if (ret == DispatchResult.NotDelivered) {
                    ret = DispatchResult.Timeout
                }
            }
            consumer.consumerFlow.emit(action)
            timeoutJob.cancel()
            ret = DispatchResult.Success
        }
        job.join()
        return ret
    }

    fun isActionAllowed(navAction: NavAction): Boolean {
        return getConsumerWithAction(navAction) != null
    }

    @Suppress("ReturnCount") // TODO clean up
    private fun getConsumerWithAction(action: NavAction): PendingActionConsumer? {
        val actionConsumer = consumer ?: return null
        val supportedGraphs = actionConsumer.owner.supportedGraphsRoutes
        if (supportedGraphs.isEmpty() || action.fromDestination == GlobalGraph) {
            return actionConsumer
        }
        if (action is NavigateAction || action is PopUpToAction) {
            if (action.fromDestination.graph.serializedRoute in supportedGraphs) {
                if (action.toDestination.graph.serializedRoute !in supportedGraphs) {
                    logger.e("Action $action toDestination belongs to different NavHost than the fromDestination.")
                    return null
                }
                return actionConsumer
            } else {
                return null
            }
        } else if (action is PopAction) {
            return actionConsumer.takeIf { action.fromDestination.graph.serializedRoute in supportedGraphs }
        } else {
            return null
        }
    }
}

private data class PendingActionConsumer(
    val owner: ActionConsumer,
    val consumerFlow: MutableSharedFlow<NavAction>,
    val scope: CoroutineScope
) {
    override fun toString(): String {
        return owner.toString()
    }
}

internal enum class DispatchResult {
    WrongNavHost,
    NotDelivered,
    Timeout,
    Success
}
