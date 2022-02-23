package com.adamkobus.compose.navigation.intent

import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.NavIntentResolver
import com.adamkobus.compose.navigation.NavigationId
import com.adamkobus.compose.navigation.action.NavOptions
import com.adamkobus.compose.navigation.destination.NavDestination

/**
 * Represents an intent navigate to certain part of the application without knowing the details required to actually perform such action.
 * This mechanism can be used in multi-module apps, tab hosts that have many edge cases, to show dev menu (if it's present),
 * do A/B testing in convenient way and so on.
 *
 * Intent on its own is not enough for navigation to happen. You must also register [NavIntentResolver] in [ComposeNavigation]
 *
 * @param name Unique id of the intent
 * @param origin to help [NavIntentResolver]s process this NavIntent, you can provide a destination that requested it.
 * @param popOptions if this intent should close a flow, then you can provide options that should result in doing that.
 * Example could be an intent that launches home screen after successful log in.
 * Such intent would contain [popOptions] that remove log in graph.
 * @param navigationId Might be helpful to some [NavIntentResolver]s to create nav action
 * @param arguments provides a way to add some extra information to the intent
 */
data class NavIntent(
    val name: String,
    val origin: NavDestination? = null,
    val popOptions: NavOptions? = null,
    val navigationId: NavigationId? = null,
    private val arguments: MutableMap<String, Any> = mutableMapOf()
) {
    /**
     * Creates a copy of [NavIntent] with [pair] added to its arguments
     */
    infix fun arg(pair: Pair<String, Any>): NavIntent = plus(pair)

    /**
     * Creates a copy of [NavIntent] with [pair] added to its arguments
     */
    operator fun plus(pair: Pair<String, Any>): NavIntent = addArgument(pair.first, pair.second)

    /**
     * Creates a copy of [NavIntent] with ([key], [value]) pair added to its arguments
     */
    fun addArgument(key: String, value: Any): NavIntent = copy(
        arguments = arguments.toMutableMap().also {
            it[key] = value
        }
    )

    /**
     * Returns a value that was previously added via [addArgument] under the same [key]
     *
     * @throws NullPointerException if argument was not found
     */
    operator fun get(key: String): Any {
        return arguments[key]!!
    }

    /**
     * Returns a value that was previously added via [addArgument] under the same [key]
     *
     * @throws NullPointerException if argument was not found
     */
    fun getString(key: String): String {
        return this[key] as String
    }

    /**
     * Returns a value that was previously added via [addArgument] under the same [key]
     *
     * @throws NullPointerException if argument was not found
     */
    fun getInt(key: String): Int {
        return this[key] as Int
    }

    /**
     * @return formatted string representation of [NavIntent]
     */
    override fun toString(): String {
        return origin?.let { originDest ->
            if (arguments.isEmpty()) {
                "$originDest[$name]"
            } else {
                "originDest[$name] $arguments"
            }
        } ?: run {
            if (arguments.isEmpty()) {
                name
            } else {
                "$name $arguments"
            }
        }
    }

    /**
     * Converts [NavIntent] to [ResolveResult.Intent]
     */
    fun asResult(): ResolveResult = ResolveResult.Intent(this)
}

internal fun navIntent(
    name: String,
    sourceDestination: NavDestination,
    reservedNameCheck: Boolean = true
): NavIntent {
    if (reservedNameCheck) {
        ComposeNavigation.getReservedNamesHandler().checkIntentName(name)
    }
    return NavIntent(name, sourceDestination)
}
