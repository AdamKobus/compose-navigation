package com.adamkobus.compose.navigation.demo.devmenu.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

internal object DevMenuPaddings {
    val Screen = 16.dp
    val TabHostSpacing = 64.dp

    val InsetsWithTabHost =
        PaddingValues(
            start = Screen,
            end = Screen,
            top = Screen,
            bottom = TabHostSpacing,
        )
}
