package com.adamkobus.compose.navigation.demo.ui.tabhost

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DemoTabData(
    val index: Int,
    @DrawableRes
    val iconResId: Int,

    @StringRes
    val titleResId: Int
)
