package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.data.NavGraph
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PendingActionDispatcher @Inject constructor() {
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
    private val consumers = mutableListOf<PendingActionConsumer>()

    fun register(owner: Any, graphs: List<NavGraph>): Flow<NavAction> {
        synchronized(consumers) {
            if (consumers.any { it.owner == owner }) {
                throw IllegalStateException("$owner is already a registered pending nav action consumer")
            }
            val flow = MutableSharedFlow<NavAction>()
            consumers.add(0, PendingActionConsumer(owner, graphs, flow, CoroutineScope(dispatcher + SupervisorJob())))
            return flow
        }
    }

    fun unregister(owner: Any) {
        synchronized(consumers) {
            consumers.find { it.owner == owner }?.let {
                it.scope.cancel()
                consumers.remove(it)
            }
        }
    }

    suspend fun dispatchAction(action: NavAction) {
        val consumer = synchronized(consumers) {
            consumers.find { it.graphs.isEmpty() || it.graphs.contains(action.fromDestination.graph) }
        }
        consumer?.let {
            withContext(it.scope.coroutineContext) {
                it.consumerFlow.emit(action)
            }
        }
    }
}

private data class PendingActionConsumer(
    val owner: Any,
    val graphs: List<NavGraph>,
    val consumerFlow: MutableSharedFlow<NavAction>,
    val scope: CoroutineScope
)
