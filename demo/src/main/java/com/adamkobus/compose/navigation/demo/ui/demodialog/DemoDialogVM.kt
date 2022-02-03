package com.adamkobus.compose.navigation.demo.ui.demodialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adamkobus.compose.navigation.NavActionConsumer
import com.adamkobus.compose.navigation.data.NavAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DemoDialogVM @Inject constructor(
    private val navActionConsumer: NavActionConsumer
) : ViewModel() {

    val interactions = DemoDialogInteractions(
        onCancelClicked = {
            viewModelScope.launch {
                navActionConsumer.offer(NavAction.Back)
            }
        }
    )
}
