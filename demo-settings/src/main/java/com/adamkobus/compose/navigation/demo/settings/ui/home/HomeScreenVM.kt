package com.adamkobus.compose.navigation.demo.settings.ui.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.adamkobus.compose.navigation.NavActionConsumer
import com.adamkobus.compose.navigation.data.NavAction
import com.adamkobus.compose.navigation.demo.settings.R
import com.adamkobus.compose.navigation.demo.ui.Elevation
import com.adamkobus.compose.navigation.demo.ui.ext.onStart
import com.adamkobus.compose.navigation.demo.ui.topbar.DemoColors
import com.adamkobus.compose.navigation.demo.ui.topbar.TopBarStateSource
import com.adamkobus.compose.navigation.demo.ui.topbar.backButton
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeScreenVM @Inject constructor(
    private val navActionConsumer: NavActionConsumer,
    private val topBarStateSource: TopBarStateSource
) : ViewModel(), LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        event.onStart {
            updateTopBar()
        }
    }

    private fun updateTopBar() {
        topBarStateSource.setUpTopBar {
            titleResId = R.string.settings_title
            background = DemoColors.PrimarySurface
            elevation = Elevation.AppBar
            backButton { onBackClicked() }
        }
    }

    private fun onBackClicked() {
        navActionConsumer.offer(NavAction.Back)
    }
}
