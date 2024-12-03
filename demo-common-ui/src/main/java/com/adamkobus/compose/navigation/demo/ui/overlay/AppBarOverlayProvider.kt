package com.adamkobus.compose.navigation.demo.ui.overlay

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBar
import javax.inject.Inject

class AppBarOverlayProvider @Inject constructor() : OverlayProvider {
    @Composable
    override fun provideOverlay(): @Composable BoxScope.() -> Unit =
        {
            AnimatedAppBar()
        }

    override val priority: Int
        get() = PRIORITY

    companion object {
        private const val PRIORITY = 100
    }
}
