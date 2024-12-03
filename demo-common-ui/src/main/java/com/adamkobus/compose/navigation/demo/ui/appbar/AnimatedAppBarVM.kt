package com.adamkobus.compose.navigation.demo.ui.appbar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimatedAppBarVM @Inject constructor(
    appBarStateSource: AppBarStateSource,
) : ViewModel() {
    val appBarState = appBarStateSource.appBarState
}
