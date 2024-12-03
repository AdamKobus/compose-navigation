package com.adamkobus.compose.navigation.demo.devmenu.ui.tabhost

internal data class DevMenuTabHostInteractions(
    val onTabSelected: (DevMenuTabData) -> Unit,
) {
    companion object {
        val STUB =
            DevMenuTabHostInteractions(
                onTabSelected = {},
            )
    }
}
