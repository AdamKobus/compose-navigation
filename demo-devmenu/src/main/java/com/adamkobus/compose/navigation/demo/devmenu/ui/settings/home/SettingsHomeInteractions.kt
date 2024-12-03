package com.adamkobus.compose.navigation.demo.devmenu.ui.settings.home

internal data class SettingsHomeInteractions(
    val onRestartAppClicked: () -> Unit,
) {
    companion object {
        val STUB =
            SettingsHomeInteractions(
                onRestartAppClicked = {},
            )
    }
}
