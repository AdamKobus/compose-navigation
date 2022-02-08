package com.adamkobus.compose.navigation.action

open class NavActionWrapper(
    val action: NavAction
) {

    override fun equals(other: Any?): Boolean {
        return other is NavActionWrapper &&
            other.action == action
    }

    override fun hashCode(): Int {
        return action.hashCode()
    }
}
