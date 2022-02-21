package com.adamkobus.compose.navigation.poc.multinavhost.green.ui.dialog

import com.adamkobus.android.vm.LifecycleAwareViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.poc.multinavhost.green.nav.GreenGraph
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GreenDialogVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer
) : LifecycleAwareViewModel() {

    val interactions = GreenDialogInteractions(
        onDismissClicked = {
            navigationConsumer.offer(GreenGraph.Dialog.pop())
        }
    )
}
