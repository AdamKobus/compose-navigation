package com.adamkobus.compose.navigation.demo.ui.topbar

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun DemoTopBarTitle(text: String?) {
    text?.let {
        Text(text = text, fontSize = 18.sp)
    }
}
