package com.adamkobus.compose.navigation

import android.util.Log
import com.adamkobus.compose.navigation.model.NavDestinationManager
import com.adamkobus.compose.navigation.model.NavLogLevel
import com.adamkobus.compose.navigation.model.NavLogger
import com.adamkobus.compose.navigation.model.NavLoggerImpl
import com.adamkobus.compose.navigation.model.ReservedNamesHandler

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

    init {
        reset()
    }

    internal fun reset() {
        reservedNames.enabled = DEFAULT_RESERVED_NAMES_ENABLED
        navLogger = DEFAULT_LOGGER
        logLevel = DEFAULT_LOG_LEVEL
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

    fun disableRestrictedNamesCheck(): ComposeNavigation {
        reservedNames.enabled = false
        return this
    }

    fun getNavDestinationManager(): NavDestinationManager {
        return destinationManager
    }

    fun getLogger(): NavLogger {
        return navLogger
    }

    internal fun getReservedNamesHandler(): ReservedNamesHandler = reservedNames
}
