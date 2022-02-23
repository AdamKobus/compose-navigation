package com.adamkobus.compose.navigation.demo.devmenu.ui.settings.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adamkobus.compose.navigation.demo.devmenu.ui.DevMenuPaddings
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground

@Composable
internal fun SettingsHome() {
    SettingsHomeContent()
}

@Composable
private fun SettingsHomeContent() {
    DemoAppBackground {
        Column(modifier = Modifier.padding(DevMenuPaddings.InsetsWithTabHost)) {
            Text(text = "Yes, the bottom navigation works. I'm glad that you checked :)")
        }
    }
}
