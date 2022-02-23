package com.adamkobus.compose.navigation.demo.ui.overlay

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable

interface OverlayProvider {
    @Composable
    fun provideOverlay(): @Composable BoxScope.() -> Unit

    val priority: Int
}
