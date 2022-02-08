package com.adamkobus.compose.navigation.demo.ui.tabhost

import androidx.compose.runtime.State

data class DemoTabsNavigationState(
    val tabHostType: State<TabHostType>,
    val animalsTabHostState: State<AnimalsTabHostState>
)

data class AnimalsTabHostState(
    val items: List<DemoTabData>,
    val selectedIndex: Int
)

enum class TabHostType {
    NONE,
    ANIMALS
}
