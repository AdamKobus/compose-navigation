package com.adamkobus.compose.navigation.ui

internal class NavComposableId private constructor(
    val id: Int
) {

    override fun equals(other: Any?): Boolean {
        return other is NavComposableId && other.id == id
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String = id.toString()

    companion object {
        private var nextId = 0

        fun next(): NavComposableId {
            synchronized(NavComposableId) {
                return NavComposableId(nextId++)
            }
        }
    }
}
