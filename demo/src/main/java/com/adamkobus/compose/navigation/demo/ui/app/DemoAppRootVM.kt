package com.adamkobus.compose.navigation.demo.ui.app

import androidx.lifecycle.ViewModel
import com.adamkobus.compose.navigation.demo.ui.nav.ApplyNavGraphsTask
import com.adamkobus.compose.navigation.demo.ui.overlay.AppOverlays
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DemoAppRootVM @Inject constructor(
    val overlays: AppOverlays,
    val applyNavGraphsTask: ApplyNavGraphsTask,
) : ViewModel()
