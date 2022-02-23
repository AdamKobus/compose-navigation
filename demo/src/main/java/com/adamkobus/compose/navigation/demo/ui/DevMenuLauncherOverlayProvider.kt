package com.adamkobus.compose.navigation.demo.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.demo.ui.nav.DemoIntents
import com.adamkobus.compose.navigation.demo.ui.nav.DemoNavigationId
import com.adamkobus.compose.navigation.demo.ui.overlay.OverlayProvider
import com.adamkobus.compose.navigation.intent.NavIntent
import javax.inject.Inject

class DevMenuLauncherOverlayProvider @Inject constructor() : OverlayProvider {

    @Composable
    override fun provideOverlay(): @Composable BoxScope.() -> Unit = {
        Launcher()
    }

    override val priority: Int
        get() = PRIORITY

    private companion object {
        private const val PRIORITY = 1000
    }
}

@Composable
private fun BoxScope.Launcher() {
    Box(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(LAUNCHER_WIDTH_FACTOR)
            .align(Alignment.TopCenter)
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = { _ ->
                        ComposeNavigation
                            .getNavigationConsumer()
                            .offer(NavIntent(DemoIntents.OPEN_DEV_MENU, navigationId = DemoNavigationId))
                    }
                )
            }
    )
}

private const val LAUNCHER_WIDTH_FACTOR = 0.45f
