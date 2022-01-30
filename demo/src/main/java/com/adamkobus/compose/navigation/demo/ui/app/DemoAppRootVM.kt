package com.adamkobus.compose.navigation.demo.ui.app

import androidx.lifecycle.ViewModel
import com.adamkobus.compose.navigation.demo.ui.topbar.TopBarStateSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DemoAppRootVM @Inject constructor(
    topBarStateSource: TopBarStateSource
) : ViewModel() {
    val topBarState = topBarStateSource.topBarState
}
