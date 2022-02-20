package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.logger.NavLogger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
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

    private var registeredConsumer: PendingActionConsumer? = null

    fun register(actionConsumer: ActionConsumer): Flow<NavAction> {
        if (registeredConsumer != null) {
            val message = "Trying to register $actionConsumer when $registeredConsumer is already registered"
            logger.e(message)
            throw IllegalStateException(message)
        }
        val flow = MutableSharedFlow<NavAction>()
        registeredConsumer = PendingActionConsumer(actionConsumer, flow, CoroutineScope(this.mainDispatcher + SupervisorJob()))
        return flow
    }

    fun unregister(actionConsumer: ActionConsumer) {
        registeredConsumer?.let {
            if (it.owner == actionConsumer) {
                registeredConsumer = null
            } else {
                throw IllegalStateException("Trying to unregister $actionConsumer when different one is registered ${it.owner}")
            }
        }
    }

    suspend fun dispatchAction(action: NavAction): DispatchResult {
        registeredConsumer?.owner?.awaitUntilReady()
        val consumer = registeredConsumer?.takeIf {
            it.owner.supportedGraphsRoutes.isEmpty() ||
                it.owner.supportedGraphsRoutes.contains(action.fromDestination.graph.route.buildRoute())
        }
        var ret = DispatchResult.NotDelivered

        if (consumer != null) {
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
        }
        return ret
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
    NotDelivered,
    Timeout,
    Success
}
