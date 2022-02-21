package com.adamkobus.compose.navigation.poc.multinavhost.green.ui.next

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.poc.multinavhost.ui.common.ScreenBackground
import com.adamkobus.compose.navigation.poc.multinavhost.ui.theme.Paddings

@Composable
fun GreenNextScreen() {
    val vm: GreenNextScreenVM = hiltViewModel()
    GreenNextScreenContent(vm.interactions)
}

@Composable
private fun GreenNextScreenContent(interactions: GreenNextScreenInteractions) {
    ScreenBackground(modifier = Modifier.padding(Paddings.Screen)) {
        Column {
            Text(
                text = "Try changing the screen orientation between Portrait and Landscape. " +
                    "Back stack state should be preserved between configuration changes by Jetpack's Navigation"
            )
            Spacer(modifier = Modifier.height(45.dp))
            Button(onClick = interactions.onOpenDialogClicked, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Open a dialog")
            }
        }
    }
}
