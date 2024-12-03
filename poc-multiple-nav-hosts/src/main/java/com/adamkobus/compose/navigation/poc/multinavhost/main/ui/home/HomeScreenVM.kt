package com.adamkobus.compose.navigation.poc.multinavhost.main.ui.home

import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.poc.multinavhost.main.nav.PocGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
) : LifecycleAwareViewModel() {
    val interactions =
        HomeScreenInteractions(
            onContinueClicked = {
                navigationConsumer.offer(PocGraph.Home goTo PocGraph.Demo)
            },
        )
}
