package com.adamkobus.compose.navigation.demo.ui.demodialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.demo.ui.nav.FromDemoDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DemoDialogVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer,
) : ViewModel() {
    val interactions =
        DemoDialogInteractions(
            onCancelClicked = {
                viewModelScope.launch {
                    navigationConsumer.offer(FromDemoDialog.Dismiss)
                }
            },
        )
}
