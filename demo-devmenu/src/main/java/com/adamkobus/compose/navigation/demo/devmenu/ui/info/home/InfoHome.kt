package com.adamkobus.compose.navigation.demo.devmenu.ui.info.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adamkobus.compose.navigation.demo.devmenu.ui.DevMenuPaddings
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground

@Composable
internal fun InfoHome() {
    InfoHomeContent()
}

@Composable
private fun InfoHomeContent() {
    DemoAppBackground {
        Column(modifier = Modifier.padding(DevMenuPaddings.InsetsWithTabHost)) {
            Text(text = "This dev menu is hosted in its own NavHost.")
        }
    }
}
