package com.adamkobus.compose.navigation

import android.util.Log
import com.adamkobus.compose.navigation.action.NavAction
import com.adamkobus.compose.navigation.error.ReservedNameError
import com.adamkobus.compose.navigation.intent.NavIntent
import com.adamkobus.compose.navigation.intent.NavIntentResolvingManager
import com.adamkobus.compose.navigation.logger.NavLogLevel
import com.adamkobus.compose.navigation.logger.NavLogger
import com.adamkobus.compose.navigation.logger.NavLoggerImpl
import com.adamkobus.compose.navigation.model.KnownDestinationsSource
import com.adamkobus.compose.navigation.model.NavDelegate
import com.adamkobus.compose.navigation.model.NavGatekeeper
import com.adamkobus.compose.navigation.model.NavStateManager
import com.adamkobus.compose.navigation.model.NavigationConsumerImpl
import com.adamkobus.compose.navigation.model.NavigationProcessor
import com.adamkobus.compose.navigation.model.ReservedNamesHandler
import com.adamkobus.compose.navigation.model.provider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Main use of this class is to configure Compose Navigation library with:
 * - [ComposeNavigation.addNavActionVerifiers]
 * - [ComposeNavigation.addNavIntentResolvers]
 * - [ComposeNavigation.setLogger]
 * - [ComposeNavigation.setLogLevel]
 * - [ComposeNavigation.setMainDispatcher]
 * - [ComposeNavigation.setIoDispatcher]
 * - [ComposeNavigation.disableRestrictedNamesCheck]
 * - [ComposeNavigation.setNavigationProcessingTimeout]
 *
 * It also gives you access to:
 * - [NavigationConsumer] via [ComposeNavigation.getNavigationConsumer] method
 * - [NavigationStateSource] via [ComposeNavigation.getNavigationStateSource]
 * - Current [NavLogger] via [ComposeNavigation.getLogger]
 */
object ComposeNavigation {

    private const val DEFAULT_RESERVED_NAMES_ENABLED = true
    private const val DEFAULT_NAVIGATION_PROCESSING_TIMEOUT_MS = 1000L

    /**
     * Default log level to which ComposeNavigation's logger is set to
     */
    @NavLogLevel
    val DEFAULT_LOG_LEVEL = Log.ERROR

    /**
     * Default logger used by ComposeNavigation.
     */
    val DEFAULT_LOGGER: NavLogger = NavLoggerImpl

    @NavLogLevel
    private var logLevel = Log.ERROR

    private var navLogger: NavLogger = DEFAULT_LOGGER
    private val reservedNames = ReservedNamesHandler()
    private val navIntentResolvingManager = NavIntentResolvingManager()
    private val navGatekeeper = NavGatekeeper()
    private var mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    private var ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    private val navigationConsumer: NavigationConsumer = NavigationConsumerImpl()
    private val knownDestinationsSource = KnownDestinationsSource()
    private val navStateManager = NavStateManager()

    private var navigationProcessingTimeout: Long = DEFAULT_NAVIGATION_PROCESSING_TIMEOUT_MS

    private val navigationProcessor: NavigationProcessor = createNavigationProcessor()

    init {
        reset()
    }

    /**
     * Resets the ComposeNavigation to initial state. This will reset:
     * - logger
     * - navigation actions verifiers
     * - navigation intent resolvers
     * - main dispatcher
     * - io dispatcher
     * - reserved names flag (it will become enabled again)
     * - navigation processing timeout
     */
    fun reset() {
        reservedNames.enabled = DEFAULT_RESERVED_NAMES_ENABLED
        navigationProcessingTimeout = DEFAULT_NAVIGATION_PROCESSING_TIMEOUT_MS
        navLogger = DEFAULT_LOGGER
        logLevel = DEFAULT_LOG_LEVEL
        navGatekeeper.reset()
        navIntentResolvingManager.reset()
        mainDispatcher = Dispatchers.Main
        ioDispatcher = Dispatchers.IO
    }

    /**
     * Changes the logger used by all [ComposeNavigation] components to [logger]
     *
     * @param logger [ComposeNavigation] and its components will start using this [NavLogger] from now on
     */
    fun setLogger(logger: NavLogger): ComposeNavigation {
        this.navLogger = logger
        logger.setLogLevel(logLevel)
        return this
    }

    /**
     * Tells the logger to use this log level. It's up to the logger itself to honor this setting or ignore it.
     *
     * @param level Log level to be used by compose navigation library. This setting is cached and any logger provided via [setLogger]
     * method is asked to use it.
     *
     * @see [NavLogLevel]
     */
    fun setLogLevel(@NavLogLevel level: Int): ComposeNavigation {
        this.logLevel = level
        navLogger.setLogLevel(level)
        return this
    }

    /**
     * Adds provided [resolvers] so that they can participate in [NavIntent]s processing.
     * The resolvers are used in the order in which you're adding them
     */
    fun addNavIntentResolvers(vararg resolvers: NavIntentResolver): ComposeNavigation = addNavIntentResolvers(resolvers.toList())

    /**
     * Adds provided [resolvers] so that they can participate in [NavIntent]s processing.
     * The resolvers are used in the order in which you're adding them
     */
    fun addNavIntentResolvers(resolvers: Collection<NavIntentResolver>): ComposeNavigation {
        navIntentResolvingManager.register(resolvers)
        return this
    }

    /**
     * Adds provided [verifiers] so that they can participate in [NavAction]s processing.
     * The verifiers are used in the order in which you're adding them
     */
    fun addNavActionVerifiers(vararg verifiers: NavActionVerifier): ComposeNavigation = addNavActionVerifiers(verifiers.toList())

    /**
     * Adds provided [verifiers] so that they can participate in [NavAction]s processing.
     * The verifiers are used in the order in which you're adding them
     */
    fun addNavActionVerifiers(verifiers: Collection<NavActionVerifier>): ComposeNavigation {
        verifiers.forEach {
            navGatekeeper.addVerifier(it)
        }
        return this
    }

    /**
     * By default, it's not possible to use destinations or intents names that start with double underscore - '__'.
     * Trying to use such names results in [ReservedNameError] being thrown.
     * You can disable this behaviour by invoking this function. Please keep in mind that this can be risky.
     * Such names were reserved in advance in case they would be needed in future, so there is no guarantee they will keep working for you.
     */
    fun disableRestrictedNamesCheck(): ComposeNavigation {
        reservedNames.enabled = false
        return this
    }

    /**
     * @param navigationId an id of [ComposeNavHost] state of which you want to access.
     *
     * @return [NavigationStateSource] managed by [ComposeNavigation]
     */
    fun getNavigationStateSource(): NavigationStateSource {
        return navStateManager
    }

    /**
     * @return currently used logger.
     */
    fun getLogger(): NavLogger {
        return navLogger
    }

    /**
     * Compose Navigation library interacts with NavHostController on main thread. This is achieved using [Dispatchers.Main] dispatcher.
     * This method allows you to change the dispatcher to a custom one.
     */
    fun setMainDispatcher(dispatcher: CoroutineDispatcher): ComposeNavigation {
        mainDispatcher = dispatcher
        return ComposeNavigation
    }

    /**
     * IO Dispatcher is used to execute navigation actions and intents processing.
     * This has to be set before any navigation processor is created.
     */
    fun setIoDispatcher(dispatcher: CoroutineDispatcher): ComposeNavigation {
        ioDispatcher = dispatcher
        return ComposeNavigation
    }

    /**
     * @return an instance of [NavigationConsumer] - a class that accepts your navigation actions or intents.
     *
     * @see [NavigationConsumer]
     */
    fun getNavigationConsumer(): NavigationConsumer {
        return navigationConsumer
    }

    /**
     * This timeout is used to interrupt navigation processing tasks that are running for too long.
     * In theory this should not happen. Timeout mechanism was introduced as a safety layer in case there is a bug in the library.
     * Timeouts are logged as errors via [NavLogger]
     *
     * @param value timeout in ms
     */
    fun setNavigationProcessingTimeout(value: Long) {
        navigationProcessingTimeout = value
    }

    internal fun getNavigationProcessingTimeout(): Long {
        return navigationProcessingTimeout
    }

    internal fun getReservedNamesHandler(): ReservedNamesHandler = reservedNames

    internal fun getNavIntentResolvingManager(): NavIntentResolvingManager = navIntentResolvingManager

    internal fun getNavGatekeeper(): NavGatekeeper = navGatekeeper

    internal fun getIoDispatcher(): CoroutineDispatcher = ioDispatcher
    internal fun getMainDispatcher(): CoroutineDispatcher = mainDispatcher

    internal fun getKnownDestinationsSource() = knownDestinationsSource

    internal fun getNavDelegate(navigationId: NavigationId): NavDelegate =
        navigationProcessor.getDispatcher(navigationId = navigationId)

    private fun createNavigationProcessor(): NavigationProcessor =
        NavigationProcessor(
            mainDispatcher = mainDispatcher,
            ioDispatcher = ioDispatcher,
            stateManager = navStateManager,
            timeoutProvider = provider { getNavigationProcessingTimeout() },
            loggerProvider = provider { getLogger() },
            navIntentResolver = navIntentResolvingManager,
            navGatekeeper = navGatekeeper
        )

    internal fun getNavProcessor(): NavigationProcessor =
        navigationProcessor
}
