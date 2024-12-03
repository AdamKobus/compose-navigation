package com.adamkobus.compose.navigation.demo.devmenu.ui.settings.home

import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.demo.devmenu.nav.tabhost.DevMenuSettingsGraph
import com.adamkobus.compose.navigation.demo.ui.nav.DemoIntents
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SettingsHomeVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
) : LifecycleAwareViewModel() {
    val interactions =
        SettingsHomeInteractions(
            onRestartAppClicked = {
                navigationConsumer.offer(DemoIntents.restartApp(origin = DevMenuSettingsGraph.Home))
            },
        )
}
