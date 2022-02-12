package com.adamkobus.compose.navigation

import android.util.Log
import com.adamkobus.compose.navigation.intent.NavIntentResolvingManager
import com.adamkobus.compose.navigation.logger.NavLogLevel
import com.adamkobus.compose.navigation.logger.NavLogger
import com.adamkobus.compose.navigation.logger.NavLoggerImpl
import com.adamkobus.compose.navigation.model.NavDestinationManager
import com.adamkobus.compose.navigation.model.NavGatekeeper
import com.adamkobus.compose.navigation.model.NavigationConsumerImpl
import com.adamkobus.compose.navigation.model.NavigationProcessor
import com.adamkobus.compose.navigation.model.PendingActionDispatcher
import com.adamkobus.compose.navigation.model.ReservedNamesHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Provides methods to configure and interact with Compose Navigation library.
 */
object ComposeNavigation {

    @NavLogLevel
    val DEFAULT_LOG_LEVEL = Log.ERROR
    val DEFAULT_LOGGER: NavLogger = NavLoggerImpl
    private const val DEFAULT_RESERVED_NAMES_ENABLED = true

    @NavLogLevel
    private var logLevel = Log.ERROR

    private var navLogger: NavLogger = DEFAULT_LOGGER
    private val destinationManager = NavDestinationManager()
    private val reservedNames = ReservedNamesHandler()
    private val navIntentResolvingManager = NavIntentResolvingManager()
    private val navGatekeeper = NavGatekeeper()
    private var mainDispatcher: CoroutineDispatcher = Dispatchers.Main

    // singleton
    private val pendingActionDispatcher = PendingActionDispatcher()
    private val navigationProcessor = NavigationProcessor()
    private val navigationConsumer: NavigationConsumer = NavigationConsumerImpl()

    init {
        reset()
    }

    internal fun reset() {
        reservedNames.enabled = DEFAULT_RESERVED_NAMES_ENABLED
        navLogger = DEFAULT_LOGGER
        logLevel = DEFAULT_LOG_LEVEL
        navGatekeeper.reset()
        mainDispatcher = Dispatchers.Main
    }

    /**
     * Changes the logger used by all [ComposeNavigation] components to [logger]
     *
     * @param [ComposeNavigation] will start using this [NavLogger] from this point.
     */
    fun setLogger(logger: NavLogger): ComposeNavigation {
        this.navLogger = logger
        logger.setLogLevel(logLevel)
        return this
    }

    /**
     * Tells the logger to use this log level. It's up to the logger itself to honor this setting or ignore it.
     * @param level Log level to use by compose navigation library. This setting is cached and any logger provided via [setLogger]
     * method is asked to use it.
     *
     * @see [NavLogLevel]
     */
    fun setLogLevel(@NavLogLevel level: Int): ComposeNavigation {
        this.logLevel = level
        navLogger.setLogLevel(level)
        return this
    }

    fun addNavIntentResolvers(vararg resolvers: NavIntentResolver): ComposeNavigation = addNavIntentResolvers(resolvers.toList())

    fun addNavIntentResolvers(resolvers: Collection<NavIntentResolver>): ComposeNavigation {
        navIntentResolvingManager.register(resolvers)
        return this
    }

    fun addNavActionVerifiers(vararg verifiers: NavActionVerifier): ComposeNavigation = addNavActionVerifiers(verifiers.toList())

    fun addNavActionVerifiers(verifiers: Collection<NavActionVerifier>): ComposeNavigation {
        verifiers.forEach {
            navGatekeeper.addVerifier(it)
        }
        return this
    }

    fun disableRestrictedNamesCheck(): ComposeNavigation {
        reservedNames.enabled = false
        return this
    }

    fun getNavigationStateSource(): NavigationStateSource {
        return destinationManager
    }

    fun getLogger(): NavLogger {
        return navLogger
    }

    /**
     * Compose Navigation library interacts with NavHostController on main thread. This is achieved using [Dispatchers.Main] dispatcher.
     * This method allows you to change the dispatcher to your own.
     */
    fun setMainDispatcher(dispatcher: CoroutineDispatcher): ComposeNavigation {
        mainDispatcher = dispatcher
        return ComposeNavigation
    }

    /**
     * Provides an instance of [NavigationConsumer] - a class that accepts your navigation actions or intents.
     *
     * @see [NavigationConsumer]
     */
    fun getNavigationConsumer(): NavigationConsumer {
        return navigationConsumer
    }

    internal fun getNavDestinationManager(): NavDestinationManager {
        return destinationManager
    }

    internal fun getReservedNamesHandler(): ReservedNamesHandler = reservedNames

    internal fun getNavIntentResolvingManager(): NavIntentResolvingManager = navIntentResolvingManager

    internal fun getNavGatekeeper(): NavGatekeeper = navGatekeeper

    internal fun getMainDispatcher(): CoroutineDispatcher = mainDispatcher

    internal fun getPendingActionDispatcher() = pendingActionDispatcher

    internal fun getNavigationProcessor() = navigationProcessor
}
