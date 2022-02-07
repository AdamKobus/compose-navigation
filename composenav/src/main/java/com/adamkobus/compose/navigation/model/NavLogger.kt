package com.adamkobus.compose.navigation.model

import android.util.Log
import androidx.annotation.IntDef

interface NavLogger {

    /**
     * Used to log details about navigation actions processing, i.e. when processing of the new actions has started and finished
     */
    fun v(message: String)

    /**
     * Used to log details about navigation actions processing, i.e. when processing of the new actions has started and finished
     */
    fun v(error: Throwable, message: String)

    /**
     * Used to log current destination changes
     */
    fun d(message: String)

    /**
     * Used to log current destination changes
     */
    fun d(error: Throwable, message: String)

    fun w(message: String)

    fun w(error: Throwable, message: String)

    fun e(message: String)

    fun e(error: Throwable, message: String)

    /**
     * When set, then logger should log only the messages with matching or higher importance than [level].
     * Setting it to [DISABLED] will disable all logging.
     *
     * @see [android.util.Log]
     * @see [NavLogLevel]
     */
    fun setLogLevel(@NavLogLevel level: Int)

    companion object {
        /**
         * All logging will be disable when [DISABLED] is provided to [setLogLevel]
         */
        const val DISABLED = 100
    }
}

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
@IntDef(Log.VERBOSE, Log.DEBUG, Log.INFO, Log.WARN, Log.ERROR, Log.ASSERT, NavLogger.DISABLED)
annotation class NavLogLevel
