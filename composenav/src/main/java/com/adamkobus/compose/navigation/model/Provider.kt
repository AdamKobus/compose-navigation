package com.adamkobus.compose.navigation.model

internal interface Provider<T> {
    fun provide(): T
}

internal fun <T> provider(source: () -> T): Provider<T> {
    return object : Provider<T> {
        override fun provide() = source()
    }
}
