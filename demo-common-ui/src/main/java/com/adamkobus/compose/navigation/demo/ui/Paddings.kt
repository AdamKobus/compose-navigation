package com.adamkobus.compose.navigation.demo.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

object Paddings {
    val Screen = 16.dp
    val CardPadding = 14.dp
    val TabHostSpacing = 64.dp

    val ScreenWithTabHostInsets =
        PaddingValues(
            top = Screen,
            start = Screen,
            end = Screen,
            bottom = TabHostSpacing,
        )
}
