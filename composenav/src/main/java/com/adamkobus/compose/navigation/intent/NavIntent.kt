package com.adamkobus.compose.navigation.intent

import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.data.NavGraph
import com.adamkobus.compose.navigation.data.ResolveResult
import com.adamkobus.compose.navigation.destination.INavDestination

/**
 * Represents an intent navigate to certain part of the application without knowing the details required to actually perform such action.
 * This mechanism can be used in multi-module apps, tab hosts that have many edge cases, to show dev menu (if it's present),
 * do A/B testing in convenient way and so on.
 *
 * Intent on its own is not enough for navigation to happen. You must also register
 * [NavIntentResolver](s) in [ComposeNavigation]
 */
data class NavIntent(
    val name: String,
    val origin: INavDestination? = null,
    private val arguments: MutableMap<String, Any> = mutableMapOf()
) {

    infix fun arg(value: Pair<String, Any>): NavIntent = plus(value)

    operator fun plus(value: Pair<String, Any>): NavIntent = addArgument(value.first, value.second)

    fun addArgument(key: String, value: Any): NavIntent = copy(
        arguments = arguments.toMutableMap().also {
            it[key] = value
        }
    )

    operator fun get(key: String): Any? {
        return arguments[key]
    }

    fun getString(key: String): String? {
        return this[key] as? String
    }

    fun getInt(key: String): Int? {
        return this[key] as? Int
    }

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

    fun asResult(): ResolveResult = ResolveResult.Intent(this)
}

internal fun navIntent(
    graph: NavGraph,
    name: String,
    reservedNameCheck: Boolean = true
): NavIntent {
    if (reservedNameCheck) {
        ComposeNavigation.getReservedNamesHandler().checkIntentName(name)
    }
    return NavIntent(name, graph)
}
