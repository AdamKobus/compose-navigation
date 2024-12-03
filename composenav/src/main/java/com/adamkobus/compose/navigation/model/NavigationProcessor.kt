package com.adamkobus.compose.navigation.model

import com.adamkobus.compose.navigation.NavigationId
import com.adamkobus.compose.navigation.action.DiscardReason
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavigateAction
import com.adamkobus.compose.navigation.action.NavigationResult
import com.adamkobus.compose.navigation.action.PopAction
import com.adamkobus.compose.navigation.action.PopUpToAction
import com.adamkobus.compose.navigation.destination.GlobalGraph
import com.adamkobus.compose.navigation.destination.NavState
import com.adamkobus.compose.navigation.intent.NavIntent
import com.adamkobus.compose.navigation.intent.NavIntentResolvingManager
import com.adamkobus.compose.navigation.logger.NavLogger
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@Suppress("LongParameterList")
internal class NavigationProcessor(
    private val mainDispatcher: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher,
    private val stateManager: NavStateManager,
    private val navIntentResolver: NavIntentResolvingManager,
    private val navGatekeeper: NavGatekeeper,
    private val loggerProvider: Provider<NavLogger>,
    private val timeoutProvider: Provider<Long>,
) {
    private val navState: NavState
        get() = stateManager.navState

    private val logger: NavLogger
        get() = loggerProvider.provide()

    private val scope: CoroutineScope = CoroutineScope(ioDispatcher + SupervisorJob())
    private val actionBuffer = MutableSharedFlow<ProcessorTask>()
    private val logPrefix = "processor:"

    private val dispatchers = mutableListOf<PendingActionDispatcher>()

    init {
        scope.launch {
            actionBuffer.collect {
                processTask(it)
            }
        }
    }

    fun getDispatcher(navigationId: NavigationId): PendingActionDispatcher {
        synchronized(dispatchers) {
            return dispatchers.find { it.navigationId == navigationId } ?: run {
                val dispatcher =
                    PendingActionDispatcher(
                        navigationId = navigationId,
                        loggerProvider = loggerProvider,
                        timeoutProvider = timeoutProvider,
                        mainDispatcher = mainDispatcher,
                        stateManager = stateManager,
                    )
                dispatchers.add(dispatcher)
                dispatcher
            }
        }
    }

    fun postNavAction(
        navAction: NavAction,
        onTaskCompleted: CompletableDeferred<NavigationResult>? = null,
    ) {
        scope.launch {
            actionBuffer.emit(ProcessorTask.Action(navAction, onTaskCompleted))
        }
    }

    fun postNavIntent(
        intent: NavIntent,
        onTaskCompleted: CompletableDeferred<NavigationResult>? = null,
    ) {
        scope.launch {
            actionBuffer.emit(ProcessorTask.Intent(intent, onTaskCompleted))
        }
    }

    private suspend fun processTask(task: ProcessorTask) {
        val result =
            when (task) {
                is ProcessorTask.Action -> processAction(task.navAction)
                is ProcessorTask.Intent -> processIntent(task.navIntent)
            }
        task.onTaskCompleted?.complete(result)
    }

    private suspend fun processIntent(navIntent: NavIntent): NavigationResult {
        logger.v("$logPrefix Started processing intent: $navIntent")
        dispatchers.forEach {
            it.awaitUntilReady()
        }

        val action = navIntentResolver.resolve(navIntent, navState)
        return if (action == null) {
            logger.w("$logPrefix Intent $navIntent was discarded, because it was not resolved into any action")
            NavigationResult.Discarded(DiscardReason.NotMapped)
        } else {
            logger.v("$logPrefix Intent $navIntent was resolved into action $action")
            processAction(action)
        }
    }

    @Suppress("ReturnCount") // TODO clean up
    private suspend fun processAction(action: NavAction): NavigationResult {
        logger.v("$logPrefix Started processing: $action")
        if (action.toDestination is GlobalGraph) {
            logger.e("$logPrefix Discarded: $action | Navigating to GlobalGraph is not allowed")
            return NavigationResult.Discarded(DiscardReason.NotDelivered)
        }

        val readyDispatchers =
            dispatchers.filter {
                it.awaitUntilReady()
            }

        val rejectingVerifier = navGatekeeper.isNavActionAllowed(navState, action)
        if (rejectingVerifier != null) {
            logger.v("$logPrefix Action discarded by verifier: $action")
            return NavigationResult.Discarded(DiscardReason.RejectedByVerifier(rejectingVerifier))
        }

        val suitableDispatchers =
            readyDispatchers.filter {
                isDispatcherSuitableForAction(it, action)
            }

        if (suitableDispatchers.isEmpty()) {
            logger.v("$logPrefix There is no NavHost suitable for action: $action")
            return NavigationResult.Discarded(DiscardReason.NotDelivered)
        }

        var result: NavigationResult = NavigationResult.Discarded(DiscardReason.NotDelivered)

        suitableDispatchers.map {
            Pair(it, it.dispatchAction(action))
        }.forEach { (dispatcher, deferredResult) ->
            val mappedResult =
                when (deferredResult.await()) {
                    DispatchResult.Success -> {
                        logger.v("$logPrefix Action $action accepted by dispatcher $dispatcher")
                        NavigationResult.Accepted
                    }
                    DispatchResult.Timeout -> {
                        logger.e("$logPrefix Timeout when trying to deliver action $action to $dispatcher")
                        NavigationResult.Discarded(DiscardReason.Timeout)
                    }
                    DispatchResult.NotDelivered -> {
                        logger.w("$logPrefix Failed to deliver action $action to $dispatcher")
                        NavigationResult.Discarded(DiscardReason.NotDelivered)
                    }
                    DispatchResult.WrongNavHost -> {
                        logger.v("$logPrefix Action cannot be processed by nav host $dispatcher: $action")
                        NavigationResult.Discarded(DiscardReason.NotDelivered)
                    }
                }
            if (result !is NavigationResult.Accepted) {
                result = mappedResult
            }
        }
        return result
    }

    private suspend fun isDispatcherSuitableForAction(
        dispatcher: PendingActionDispatcher,
        action: NavAction,
    ): Boolean {
        val supportedGraphs = dispatcher.getSupportedGraphRoutes() ?: return false
        return if (action is NavigateAction || action is PopUpToAction) {
            action.toDestination.graph.serializedRoute in supportedGraphs
        } else if (action is PopAction) {
            action.fromDestination.graph.serializedRoute in supportedGraphs
        } else {
            false
        }
    }
}
