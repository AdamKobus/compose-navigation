package com.adamkobus.compose.navigation.demo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun LifecycleAwareComponent(observer: LifecycleEventObserver) {
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    DisposableEffect(lifecycleOwner.value) {
        lifecycleOwner.value.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.value.lifecycle.removeObserver(observer)
        }
    }
}
