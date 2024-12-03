package com.adamkobus.compose.navigation.logger

import android.util.Log
import com.adamkobus.compose.navigation.ComposeNavigation

internal object NavLoggerImpl : NavLogger {
    private const val TAG = "Navigation"

    private var logLevel: Int = ComposeNavigation.DEFAULT_LOG_LEVEL

    override fun v(message: String) {
        if (canLog(Log.VERBOSE)) {
            Log.v(TAG, message)
        }
    }

    override fun v(
        error: Throwable,
        message: String,
    ) {
        if (canLog(Log.VERBOSE)) {
            Log.v(TAG, message, error)
        }
    }

    override fun d(message: String) {
        if (canLog(Log.DEBUG)) {
            Log.d(TAG, message)
        }
    }

    override fun d(
        error: Throwable,
        message: String,
    ) {
        if (canLog(Log.DEBUG)) {
            Log.d(TAG, message, error)
        }
    }

    override fun w(message: String) {
        if (canLog(Log.WARN)) {
            Log.w(TAG, message)
        }
    }

    override fun w(
        error: Throwable,
        message: String,
    ) {
        if (canLog(Log.WARN)) {
            Log.w(TAG, message, error)
        }
    }

    override fun e(message: String) {
        if (canLog(Log.ERROR)) {
            Log.e(TAG, message)
        }
    }

    override fun e(
        error: Throwable,
        message: String,
    ) {
        if (canLog(Log.ERROR)) {
            Log.e(TAG, message, error)
        }
    }

    override fun setLogLevel(level: Int) {
        logLevel = level
    }

    private fun canLog(
        @NavLogLevel level: Int,
    ) = level >= logLevel
}
