package com.adamkobus.compose.navigation.demo.ui.tabhost

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.adamkobus.compose.navigation.demo.ui.overlay.OverlayProvider
import javax.inject.Inject

class DemoTabsNavigationOverlayProvider @Inject constructor() : OverlayProvider {
    @Composable
    override fun provideOverlay(): @Composable BoxScope.() -> Unit =
        {
            DemoTabsNavigation(modifier = Modifier.align(Alignment.BottomCenter))
        }

    override val priority: Int = PRIORITY

    private companion object {
        private const val PRIORITY = 100
    }
}
