package com.adamkobus.compose.navigation.ext

import androidx.lifecycle.Lifecycle

internal fun Lifecycle.Event.onStartStop(onStart: () -> Unit, onStop: () -> Unit) {
    if (this == Lifecycle.Event.ON_START) {
        onStart()
    } else if (this == Lifecycle.Event.ON_STOP) {
        onStop()
    }
}
