package com.adamkobus.compose.navigation.demo.ui.topbar

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class TopBarIconState(
    var icon: ImageVector? = null,
    var onClick: () -> Unit = {},
    @StringRes
    var contentDescription: Int? = null
)
