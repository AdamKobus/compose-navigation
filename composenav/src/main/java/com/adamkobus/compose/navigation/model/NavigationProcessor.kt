package com.adamkobus.compose.navigation.model

import androidx.navigation.NavBackStackEntry
import com.adamkobus.compose.navigation.NavigationId
import com.adamkobus.compose.navigation.action.DiscardReason
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.action.NavigationResult
import com.adamkobus.compose.navigation.destination.GlobalGraph
import com.adamkobus.compose.navigation.destination.NavState
import com.adamkobus.compose.navigation.intent.NavIntent
import com.adamkobus.compose.navigation.intent.NavIntentResolvingManager
import com.adamkobus.compose.navigation.logger.NavLogger
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@Suppress("LongParameterList")
internal class NavigationProcessor(
    val navigationId: NavigationId,
    ioDispatcher: CoroutineDispatcher,
    private val stateManager: NavStateManager,
    private val navIntentResolver: NavIntentResolvingManager,
    private val navGatekeeper: NavGatekeeper,
    private val actionDispatcher: PendingActionDispatcher,
    private val loggerProvider: Provider<NavLogger>,
    private val timeoutProvider: Provider<Long>
) {
    private val navState: NavState
        get() = stateManager.navState

    private val logger: NavLogger
        get() = loggerProvider.provide()

    private val actionProcessingTimeout: Long
        get() = timeoutProvider.provide()

    private val scope: CoroutineScope = CoroutineScope(ioDispatcher + SupervisorJob())
    private val actionBuffer = MutableSharedFlow<ProcessorTask>()
    private var onActionPerformed: CompletableDeferred<Boolean>? = null
    private val logPrefix = "[$navigationId]"
    private var initState = ProcessorInitializedState()

    init {
        scope.launch {
            actionBuffer.collect {
                processTask(it)
            }
        }
    }

    private var currentActionConsumer: ActionConsumer? = null

    fun register(actionConsumer: ActionConsumer): Flow<NavAction> {
        currentActionConsumer?.let {
            unregister(it)
        }
        currentActionConsumer = actionConsumer
        logger.v("$logPrefix Registering action consumer: $actionConsumer")
        initState.onRegistered()
        return actionDispatcher.register(actionConsumer)
    }

    fun unregister(actionConsumer: ActionConsumer) {
        this.currentActionConsumer?.let {
            if (it == actionConsumer) {
                logger.v("$logPrefix Unregistering action consumer: $actionConsumer")
                initState.onUnregistered()
                actionDispatcher.unregister(actionConsumer)
                currentActionConsumer = null
            }
        }
    }

    fun onBackStackCleared(actionConsumer: ActionConsumer) {
        this.currentActionConsumer?.let {
            if (it == actionConsumer) {
                stateManager.onBackStackUpdated(navigationId, null, emptyList())
            }
        }
    }

    fun postNavAction(navAction: NavAction, onTaskCompleted: CompletableDeferred<NavigationResult>? = null) {
        scope.launch {
            if (actionDispatcher.isActionAllowed(navAction)) {
                actionBuffer.emit(ProcessorTask.Action(navAction, onTaskCompleted))
            }
        }
    }

    fun postNavIntent(intent: NavIntent, onTaskCompleted: CompletableDeferred<NavigationResult>? = null) {
        scope.launch {
            actionBuffer.emit(ProcessorTask.Intent(intent, onTaskCompleted))
        }
    }

    private suspend fun processTask(task: ProcessorTask) {
        val result = when (task) {
            is ProcessorTask.Action -> processAction(task.navAction)
            is ProcessorTask.Intent -> processIntent(task.navIntent)
        }
        task.onTaskCompleted?.complete(result)
    }

    private suspend fun processIntent(navIntent: NavIntent): NavigationResult {
        logger.v("$logPrefix Started processing intent: $navIntent")
        if (!initState.isInitialized()) {
            logger.w("$logPrefix Intent $navIntent was discarded, because nav host is not available")
            return NavigationResult.Discarded(DiscardReason.NotDelivered)
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
        if (!initState.isInitialized()) {
            logger.w("$logPrefix Action $action was discarded, because nav host is not available")
            return NavigationResult.Discarded(DiscardReason.NotDelivered)
        }
        if (action.toDestination is GlobalGraph) {
            logger.e("$logPrefix Discarded: $action | Navigating to GlobalGraph is not allowed")
            return NavigationResult.Discarded(DiscardReason.NotDelivered)
        }
        val rejectingVerifier = navGatekeeper.isNavActionAllowed(navState, action)
        if (rejectingVerifier != null) {
            logger.v("$logPrefix Action discarded by verifier: $action")
            return NavigationResult.Discarded(DiscardReason.RejectedByVerifier(rejectingVerifier))
        }
        val actionCompletedDeferred = CompletableDeferred<Boolean>()
        onActionPerformed = actionCompletedDeferred
        return when (actionDispatcher.dispatchAction(action = action)) {
            DispatchResult.Success -> {
                logger.v("$logPrefix Action delivered: $action")
                val timeoutTask = createTimeoutTask(actionCompletedDeferred)
                val result = actionCompletedDeferred.await()
                timeoutTask.cancel()
                if (result) {
                    logger.v("$logPrefix Stopped processing: $action")
                    NavigationResult.Accepted
                } else {
                    logger.e("$logPrefix Timeout when waiting for back stack update from action: $action")
                    NavigationResult.Discarded(DiscardReason.Timeout)
                }
            }
            DispatchResult.Timeout -> {
                logger.e("$logPrefix Timeout when trying to deliver action: $action")
                NavigationResult.Discarded(DiscardReason.Timeout)
            }
            DispatchResult.NotDelivered -> {
                logger.w("$logPrefix Failed to deliver action: $action")
                NavigationResult.Discarded(DiscardReason.NotDelivered)
            }
            DispatchResult.WrongNavHost -> {
                logger.v("$logPrefix Action cannot be processed by this NavHost: $action")
                NavigationResult.Discarded(DiscardReason.NotDelivered)
            }
        }
    }

    fun onBackStackEntryUpdated(entry: NavBackStackEntry?, backQueue: List<NavBackStackEntry>) {
        if (initState.isRegistered()) {
            stateManager.onBackStackUpdated(navigationId, entry, backQueue)
            initState.onInitComplete()
            onActionPerformed?.complete(true)
            onActionPerformed = null
        }
    }

    fun onActionCompletedWithoutBackStackUpdate() {
        onActionPerformed?.complete(true)
        onActionPerformed = null
    }

    private fun createTimeoutTask(completable: CompletableDeferred<Boolean>): Job {
        return scope.launch {
            delay(actionProcessingTimeout)
            completable.complete(false)
        }
    }
}
