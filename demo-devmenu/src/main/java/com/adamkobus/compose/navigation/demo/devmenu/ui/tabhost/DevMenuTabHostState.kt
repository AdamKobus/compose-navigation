package com.adamkobus.compose.navigation.demo.devmenu.ui.tabhost

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

internal data class DevMenuTabHostState(
    val isVisible: State<Boolean>,
    val tabs: State<List<DevMenuTabData>>,
    val selectedIndex: State<Int>
) {
    companion object {
        fun stub(
            isVisible: Boolean = true,
            tabs: List<DevMenuTabData> = listOf(
                DevMenuTabs.Info,
                DevMenuTabs.Settings
            ),
            selectedIndex: Int = 0
        ) = DevMenuTabHostState(
            isVisible = mutableStateOf(isVisible),
            tabs = mutableStateOf(tabs),
            selectedIndex = mutableStateOf(selectedIndex)
        )
    }
}
