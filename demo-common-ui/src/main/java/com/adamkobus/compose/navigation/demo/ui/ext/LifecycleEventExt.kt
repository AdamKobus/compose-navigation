package com.adamkobus.compose.navigation.demo.ui.ext

import androidx.lifecycle.Lifecycle

fun Lifecycle.Event.onStartStop(
    onStart: () -> Unit,
    onStop: () -> Unit,
) {
    if (this == Lifecycle.Event.ON_START) {
        onStart()
    } else if (this == Lifecycle.Event.ON_STOP) {
        onStop()
    }
}

fun Lifecycle.Event.onStart(onStart: () -> Unit) {
    if (this == Lifecycle.Event.ON_START) {
        onStart()
    }
}
