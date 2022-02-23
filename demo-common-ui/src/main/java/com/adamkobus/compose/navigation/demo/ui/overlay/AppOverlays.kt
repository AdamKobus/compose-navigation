package com.adamkobus.compose.navigation.demo.ui.overlay

import javax.inject.Inject

class AppOverlays @Inject constructor(
    private val providersSet: Set<@JvmSuppressWildcards OverlayProvider>
) {
    val providers: List<OverlayProvider>
        get() = providersSet.sortedBy { it.priority }
}
