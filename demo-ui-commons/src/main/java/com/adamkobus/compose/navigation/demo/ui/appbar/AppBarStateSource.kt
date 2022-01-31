package com.adamkobus.compose.navigation.demo.ui.appbar

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppBarStateSource @Inject constructor() {
    private val _appBarState = mutableStateOf(AnimatedAppBarState())
    val appBarState: State<AnimatedAppBarState> = _appBarState

    fun offer(state: AnimatedAppBarState) {
        _appBarState.value = state
    }
}
