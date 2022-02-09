package com.adamkobus.compose.navigation.demo.settings.ui.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarIconState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarTitleState
import com.adamkobus.compose.navigation.demo.ui.ext.onStart
import com.adamkobus.compose.navigation.demo.ui.nav.FromSettingsHome
import com.adamkobus.compose.navigation.demo.ui.settings.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
    private val appBarStateSource: AppBarStateSource
) : ViewModel(), LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        event.onStart {
            updateTopBar()
        }
    }

    private val appBarState = AnimatedAppBarState(
        titleState = AppBarTitleState(titleResId = R.string.settings_title),
        iconState = AppBarIconState.back { onBackClicked() }
    )

    private fun updateTopBar() {
        appBarStateSource.offer(appBarState)
    }

    private fun onBackClicked() {
        navigationConsumer.offer(FromSettingsHome.Back)
    }
}
