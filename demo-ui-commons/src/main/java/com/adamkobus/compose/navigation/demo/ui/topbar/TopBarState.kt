package com.adamkobus.compose.navigation.demo.ui.topbar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adamkobus.compose.navigation.demo.ui.R

data class TopBarState(
    @StringRes
    var titleResId: Int? = null,
    var titleRaw: String? = null,
    var iconState: TopBarIconState = TopBarIconState(),
    var elevation: Dp = 0.dp,
    val actions: MutableList<TopBarAction> = mutableListOf(),
    var background: DemoColors = DemoColors.None
) {
    fun action(init: TopBarAction.() -> Unit) {
        val action = TopBarAction().apply(init)
        actions.add(action)
    }
}

fun TopBarState.iconState(init: TopBarIconState.() -> Unit) {
    val ret = this.iconState
    ret.init()
    this.iconState = ret
}

fun TopBarState.backButton(clickAction: () -> Unit) {
    iconState {
        icon = Icons.Filled.ArrowBack
        contentDescription = R.string.accessibility_back
        onClick = clickAction
    }
}
