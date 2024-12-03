package com.adamkobus.compose.navigation.demo.devmenu.ui.settings.home

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
import com.adamkobus.compose.navigation.demo.devmenu.ui.DevMenuPaddings
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground

@Composable
internal fun SettingsHome() {
    val vm: SettingsHomeVM = hiltViewModel()
    SettingsHomeContent(vm.interactions)
}

@Composable
private fun SettingsHomeContent(interactions: SettingsHomeInteractions = SettingsHomeInteractions.STUB) {
    DemoAppBackground {
        Column(modifier = Modifier.padding(DevMenuPaddings.InsetsWithTabHost)) {
            Text(
                text =
                "Button below will invoke intent that will be translated into action that has " +
                    "different source and target nav controllers, but action will still succeed",
            )
            Spacer(modifier = Modifier.height(60.dp))
            Button(
                onClick = interactions.onRestartAppClicked,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Restart app")
            }
        }
    }
}
