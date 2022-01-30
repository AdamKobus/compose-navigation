package com.adamkobus.compose.navigation.demo.ui.topbar

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopBarStateSource @Inject constructor() {
    private val _topBarState = mutableStateOf(TopBarState())
    val topBarState: State<TopBarState> = _topBarState

    fun setUpTopBar(topBarInit: TopBarState.() -> Unit) {
        _topBarState.value = TopBarState().apply(topBarInit)
    }
}
