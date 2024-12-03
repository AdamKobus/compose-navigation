package com.adamkobus.compose.navigation.demo.devmenu.ui.root

import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.nav.DemoIntents
import com.adamkobus.compose.navigation.intent.NavIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DevMenuRootVM @Inject constructor(
    private val appBarStateSource: AppBarStateSource,
    private val navigationConsumer: NavigationConsumer,
) : LifecycleAwareViewModel() {
    val interactions =
        DevMenuRootInteractions(
            onBackPressed = {
                navigationConsumer.offer(NavIntent(DemoIntents.CLOSE_DEV_MENU))
            },
        )

    init {
        runOnStart {
            appBarStateSource.offer(AnimatedAppBarState())
        }
    }
}
