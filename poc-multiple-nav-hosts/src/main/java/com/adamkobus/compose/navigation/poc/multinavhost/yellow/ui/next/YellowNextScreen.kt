package com.adamkobus.compose.navigation.poc.multinavhost.yellow.ui.next

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.poc.multinavhost.ui.common.ScreenBackground
import com.adamkobus.compose.navigation.poc.multinavhost.ui.theme.Paddings

@Composable
fun YellowNextScreen() {
    val vm: YellowNextScreenVM = hiltViewModel()
    YellowNextScreenContent(vm.interactions)
}

@Composable
private fun YellowNextScreenContent(interactions: YellowNextScreenInteractions) {
    ScreenBackground(modifier = Modifier.padding(Paddings.Screen)) {
        Column {
            Text(text = "Now try using back navigation")
            Spacer(modifier = Modifier.height(60.dp))
            Button(onClick = interactions.onOpenDialogClicked, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Open a dialog")
            }
        }
    }
}
