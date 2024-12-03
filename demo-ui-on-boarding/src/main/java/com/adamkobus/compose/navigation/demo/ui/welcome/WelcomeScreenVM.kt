package com.adamkobus.compose.navigation.demo.ui.welcome

import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.demo.ui.appbar.AnimatedAppBarState
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarStateSource
import com.adamkobus.compose.navigation.demo.ui.nav.FromWelcome
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
    private val appBarStateSource: AppBarStateSource,
) : LifecycleAwareViewModel() {
    val interactions =
        WelcomeScreenInteractions(
            onDogsSelected = {
                navigationConsumer.offer(FromWelcome.ToDogsList)
            },
            onCatsSelected = {
                navigationConsumer.offer(FromWelcome.ToCatsList)
            },
        )

    private val appBatState = AnimatedAppBarState()

    init {
        runOnStart {
            appBarStateSource.offer(appBatState)
        }
    }
}
