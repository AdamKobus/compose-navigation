package com.adamkobus.compose.navigation.destination

import com.adamkobus.compose.navigation.error.NavArgumentFormatInvalidError
import com.adamkobus.compose.navigation.error.NavArgumentMissingError

/**
 * This class holds information about single entry in navigation back stack.
 *
 * If destination has any arguments in its route, then it's guaranteed that their values will be present in [arguments].
 * That's because Compose Navigation library does not support optional arguments by design.
 * Assumption is that any param that has been declared in the route must have a value assigned during navigation.
 *
 * @param destination destination linked to this back stack entry
 * @param arguments args with which this destination was launched
 */
data class NavStackEntry(
    val destination: NavDestination,
    val arguments: Map<String, String>
) {
    /**
     * Returns a String representation of [NavStackEntry]
     */
    override fun toString(): String {
        return destination.route.buildPath(arguments.values.toList())
    }

    /**
     * @param key Name by which this param can be identified in destination's route
     * @return String param embedded into this destination's route
     *
     * @throws NavArgumentMissingError if argument with provided key does not exist
     */
    fun getString(key: String): String =
        this.arguments[key]
            ?: throw NavArgumentMissingError("Destination '${this.destination}' did not receive value for argument with key '$key'")

    /**
     * @param key Name by which this param can be identified in destination's route
     * @return Int value embedded into this destination's route
     *
     * @throws NavArgumentMissingError if argument with provided key does not exist
     * @throws NavArgumentFormatInvalidError if argument could not be parsed to Int
     */
    fun getInt(key: String): Int {
        val stringValue = getString(key)
        try {
            return stringValue.toInt()
        } catch (e: NumberFormatException) {
            throw NavArgumentFormatInvalidError(
                "Value '$stringValue' of argument '$key' in destination '${this.destination}' could not be parsed to Int"
            )
        }
    }

    /**
     * @param key Name by which this param can be identified in destination's route
     * @return Long value embedded into this destination's route
     *
     * @throws NavArgumentMissingError if argument with provided key does not exist
     * @throws NavArgumentFormatInvalidError if argument could not be parsed to Long
     */
    fun getLong(key: String): Long {
        val stringValue = getString(key)
        try {
            return stringValue.toLong()
        } catch (e: NumberFormatException) {
            throw NavArgumentFormatInvalidError(
                "Value '$stringValue' of argument '$key' in destination '${this.destination}' could not be parsed to Long"
            )
        }
    }

    /**
     * @param key Name by which this param can be identified in destination's route
     * @return Float value embedded into this destination's route
     *
     * @throws NavArgumentMissingError if argument with provided key does not exist
     * @throws NavArgumentFormatInvalidError if argument could not be parsed to Float
     */
    fun getFloat(key: String): Float {
        val stringValue = getString(key)
        try {
            return stringValue.toFloat()
        } catch (e: NumberFormatException) {
            throw NavArgumentFormatInvalidError(
                "Value '$stringValue' of argument '$key' in destination '${this.destination}' could not be parsed to Float"
            )
        }
    }

    /**
     * @param key Name by which this param can be identified in destination's route
     * @return Double value embedded into this destination's route
     *
     * @throws NavArgumentMissingError if argument with provided key does not exist
     * @throws NavArgumentFormatInvalidError if argument could not be parsed to Double
     */
    fun getDouble(key: String): Double {
        val stringValue = getString(key)
        try {
            return stringValue.toDouble()
        } catch (e: NumberFormatException) {
            throw NavArgumentFormatInvalidError(
                "Value '$stringValue' of argument '$key' in destination '${this.destination}' could not be parsed to Double"
            )
        }
    }

    /**
     * @param key Name by which this param can be identified in destination's route
     * @return Double value embedded into this destination's route
     *
     * @throws NavArgumentMissingError if argument with provided key does not exist
     * @throws NavArgumentFormatInvalidError if argument could not be parsed to Double
     */
    fun getBoolean(key: String): Boolean {
        return when (val stringValue = getString(key)) {
            "true" -> true
            "false" -> false
            else -> throw NavArgumentFormatInvalidError(
                "Value '$stringValue' of argument '$key' in destination '${this.destination}' could not be parsed to Boolean"
            )
        }
    }
}
