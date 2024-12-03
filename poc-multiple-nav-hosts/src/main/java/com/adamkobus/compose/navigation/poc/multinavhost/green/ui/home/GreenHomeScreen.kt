package com.adamkobus.compose.navigation.poc.multinavhost.green.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.poc.multinavhost.ui.common.ScreenBackground
import com.adamkobus.compose.navigation.poc.multinavhost.ui.theme.Paddings

@Composable
fun GreenHomeScreen() {
    val vm: GreenHomeScreenVM = hiltViewModel()
    GreenHomeScreenContent(vm.interactions)
}

@Composable
private fun GreenHomeScreenContent(interactions: GreenHomeScreenInteractions) {
    ScreenBackground(
        modifier = Modifier.padding(Paddings.Screen),
    ) {
        Button(onClick = interactions.onNextClicked, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Go to the next screen")
        }
    }
}
