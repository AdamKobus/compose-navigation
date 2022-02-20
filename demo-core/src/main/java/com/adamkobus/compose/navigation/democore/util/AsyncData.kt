package com.adamkobus.compose.navigation.democore.util

sealed class AsyncData<T, E> {
    class Loading<T, E> : AsyncData<T, E>()
    class Present<T, E>(val data: T) : AsyncData<T, E>()
    class Missing<T, E>(val error: E? = null) : AsyncData<T, E>()
}
