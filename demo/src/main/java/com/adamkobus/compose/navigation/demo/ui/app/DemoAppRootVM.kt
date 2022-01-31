package com.adamkobus.compose.navigation.demo.ui.app

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DemoAppRootVM @Inject constructor(
    private val appBarStateSource: AppBarStateSource
) : ViewModel() {
    val appBarState: State<AnimatedAppBarState>
        get() = appBarStateSource.appBarState
}
