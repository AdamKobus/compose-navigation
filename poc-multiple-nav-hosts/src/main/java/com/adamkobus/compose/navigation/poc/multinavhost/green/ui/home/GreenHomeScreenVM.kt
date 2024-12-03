package com.adamkobus.compose.navigation.poc.multinavhost.green.ui.home

import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.poc.multinavhost.green.nav.GreenGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GreenHomeScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
) : LifecycleAwareViewModel() {
    val interactions =
        GreenHomeScreenInteractions(
            onNextClicked = {
                navigationConsumer.offer(GreenGraph.Home goTo GreenGraph.Next)
            },
        )
}
