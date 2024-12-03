package com.adamkobus.compose.navigation.poc.multinavhost.green.ui.next

import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.poc.multinavhost.green.nav.GreenGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GreenNextScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
) : LifecycleAwareViewModel() {
    val interactions =
        GreenNextScreenInteractions(
            onOpenDialogClicked = {
                navigationConsumer.offer(GreenGraph.Next goTo GreenGraph.Dialog)
            },
        )
}
