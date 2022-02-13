package com.adamkobus.compose.navigation.action

import com.adamkobus.compose.navigation.NavigationConsumer

/**
 * This can be used to build navigation actions using sealed class.
 * The advantage of using [NavActionWrapper] is that it's a structure that can be offered to [NavigationConsumer].
 * It also implements [equals] and [hashCode] methods, so that you don't have to do it in your sealed classes.
 *
 * @param action actual action that will be processed when offered to [NavigationConsumer]
 */
open class NavActionWrapper(
    val action: NavAction
) {

    /**
     * Compares other [NavActionWrapper] by [action] field
     */
    override fun equals(other: Any?): Boolean {
        return other is NavActionWrapper &&
            other.action == action
    }

    /**
     * Builds hash code based on [action] field
     */
    override fun hashCode(): Int {
        return action.hashCode()
    }

    /**
     * @return formatted String representation of [NavActionWrapper]
     */
    override fun toString(): String {
        return "Wrapper: $action"
    }
}
