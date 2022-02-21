package com.adamkobus.compose.navigation.poc.multinavhost.yellow.ui.dialog

import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.poc.multinavhost.yellow.nav.YellowGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class YellowDialogVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer
) : LifecycleAwareViewModel() {

    val interactions = YellowDialogInteractions(
        onDismissClicked = {
            navigationConsumer.offer(YellowGraph.Dialog.pop())
        }
    )
}
