package com.adamkobus.compose.navigation.poc.multinavhost.yellow.ui.home

import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.poc.multinavhost.yellow.nav.YellowGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YellowHomeScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
) : LifecycleAwareViewModel() {
    val interactions =
        YellowHomeScreenInteractions(
            onNextClicked = {
                navigationConsumer.offer(YellowGraph.Home goTo YellowGraph.Next)
            },
        )
}
