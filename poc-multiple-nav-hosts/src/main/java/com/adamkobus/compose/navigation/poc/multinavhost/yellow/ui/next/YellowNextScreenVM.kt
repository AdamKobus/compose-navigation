package com.adamkobus.compose.navigation.poc.multinavhost.yellow.ui.next

import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.poc.multinavhost.yellow.nav.YellowGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YellowNextScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer
) : LifecycleAwareViewModel() {

    val interactions = YellowNextScreenInteractions(
        onOpenDialogClicked = {
            navigationConsumer.offer(YellowGraph.Next goTo YellowGraph.Dialog)
        }
    )
}
