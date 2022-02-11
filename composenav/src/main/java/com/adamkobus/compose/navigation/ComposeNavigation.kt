package com.adamkobus.compose.navigation

import android.util.Log
import com.adamkobus.compose.navigation.intent.NavIntentResolvingManager
import com.adamkobus.compose.navigation.model.NavDestinationManager
import com.adamkobus.compose.navigation.model.NavGatekeeper
import com.adamkobus.compose.navigation.model.NavLogLevel
import com.adamkobus.compose.navigation.model.NavLogger
import com.adamkobus.compose.navigation.model.NavLoggerImpl
import com.adamkobus.compose.navigation.model.NavigationConsumerImpl
import com.adamkobus.compose.navigation.model.NavigationProcessor
import com.adamkobus.compose.navigation.model.PendingActionDispatcher
import com.adamkobus.compose.navigation.model.ReservedNamesHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Provides methods to configure Compose Navigation library.
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

    fun setLogger(logger: NavLogger): ComposeNavigation {
        this.navLogger = logger
        logger.setLogLevel(logLevel)
        return this
    }

    fun setLogLevel(@NavLogLevel level: Int): ComposeNavigation {
        this.logLevel = level
        navLogger.setLogLevel(level)
        return this
    }

    fun addNavIntentResolvers(vararg resolvers: NavIntentResolver): ComposeNavigation {
        navIntentResolvingManager.register(*resolvers)
        return this
    }

    fun addNavActionVerifiers(vararg verifier: NavActionVerifier): ComposeNavigation {
        verifier.forEach {
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

    fun setMainDispatcher(dispatcher: CoroutineDispatcher): ComposeNavigation {
        mainDispatcher = dispatcher
        return ComposeNavigation
    }

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
