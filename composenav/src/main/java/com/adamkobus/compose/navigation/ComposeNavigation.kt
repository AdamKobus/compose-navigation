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
import com.adamkobus.compose.navigation.model.NavGatekeeper
import com.adamkobus.compose.navigation.model.NavHostComponents
import com.adamkobus.compose.navigation.model.NavStateManager
import com.adamkobus.compose.navigation.model.NavigationConsumerImpl
import com.adamkobus.compose.navigation.model.NavigationProcessor
import com.adamkobus.compose.navigation.model.PendingActionDispatcher
import com.adamkobus.compose.navigation.model.ReservedNamesHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Main use of this class is to configure Compose Navigation library with:
 * - [ComposeNavigation.addNavActionVerifiers]
 * - [ComposeNavigation.addNavIntentResolvers]
 * - [ComposeNavigation.setLogger]
 * - [ComposeNavigation.setLogLevel]
 * - [ComposeNavigation.setMainDispatcher]
 * - [ComposeNavigation.disableRestrictedNamesCheck]
 *
 * It also gives you access to:
 * - [NavigationConsumer] via [ComposeNavigation.getNavigationConsumer] method
 * - [NavigationStateSource] via [ComposeNavigation.getNavigationStateSource]
 * - Current [NavLogger] via [ComposeNavigation.getLogger]
 */
object ComposeNavigation {

    private const val DEFAULT_RESERVED_NAMES_ENABLED = true

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

    private val pendingActionDispatcher = PendingActionDispatcher()
    private val navigationConsumer: NavigationConsumer = NavigationConsumerImpl()
    private val knownDestinationsSource = KnownDestinationsSource()

    private val components = mutableListOf<NavHostComponents>()

    init {
        reset()
    }

    /**
     * Resets the ComposeNavigation to initial state. This will reset:
     * - logger
     * - navigation actions verifiers
     * - navigation intent resolvers
     * - main dispatcher
     * - reserved names flag (it will become enabled again)
     */
    fun reset() {
        reservedNames.enabled = DEFAULT_RESERVED_NAMES_ENABLED
        navLogger = DEFAULT_LOGGER
        logLevel = DEFAULT_LOG_LEVEL
        navGatekeeper.reset()
        navIntentResolvingManager.reset()
        mainDispatcher = Dispatchers.Main
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
    fun getNavigationStateSource(navigationId: NavigationId): NavigationStateSource {
        return getNavDestinationManager(navigationId)
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
     * @return an instance of [NavigationConsumer] - a class that accepts your navigation actions or intents.
     *
     * @see [NavigationConsumer]
     */
    fun getNavigationConsumer(): NavigationConsumer {
        return navigationConsumer
    }

    internal fun getNavDestinationManager(navigationId: NavigationId): NavStateManager {
        return getComponentsFor(navigationId).destinationManager
    }

    internal fun getReservedNamesHandler(): ReservedNamesHandler = reservedNames

    internal fun getNavIntentResolvingManager(): NavIntentResolvingManager = navIntentResolvingManager

    internal fun getNavGatekeeper(): NavGatekeeper = navGatekeeper

    internal fun getMainDispatcher(): CoroutineDispatcher = mainDispatcher

    internal fun getPendingActionDispatcher() = pendingActionDispatcher

    internal fun getNavigationProcessor(navigationId: NavigationId) = getComponentsFor(navigationId).navigationProcessor

    internal fun getKnownDestinationsSource() = knownDestinationsSource

    internal fun getAllNavProcessors(): List<NavigationProcessor> {
        synchronized(components) {
            return components.map { it.navigationProcessor }
        }
    }

    private fun getComponentsFor(navigationId: NavigationId): NavHostComponents {
        synchronized(components) {
            return components.find { it.navigationId == navigationId } ?: run {
                val newComponent = NavHostComponents(navigationId)
                components.add(newComponent)
                newComponent
            }
        }
    }
}
