package com.adamkobus.compose.navigation.demo.settings.ui.home

import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarIconState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarTitleState
import com.adamkobus.compose.navigation.demo.ui.nav.FromSettingsHome
import com.adamkobus.compose.navigation.demo.ui.settings.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
    private val appBarStateSource: AppBarStateSource
) : LifecycleAwareViewModel() {

    private val appBarState = AnimatedAppBarState(
        titleState = AppBarTitleState(titleResId = R.string.settings_title),
        iconState = AppBarIconState.back { onBackClicked() }
    )

    init {
        runOnStart {
            appBarStateSource.offer(appBarState)
        }
    }

    private fun onBackClicked() {
        navigationConsumer.offer(FromSettingsHome.Back)
    }
}
