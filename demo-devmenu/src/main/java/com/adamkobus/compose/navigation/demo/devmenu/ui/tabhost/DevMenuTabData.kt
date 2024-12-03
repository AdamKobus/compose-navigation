package com.adamkobus.compose.navigation.demo.devmenu.ui.tabhost

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

internal data class DevMenuTabData(
    val index: Int,
    @DrawableRes
    val iconResId: Int,
    @StringRes
    val titleResId: Int,
)
