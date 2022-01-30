package com.adamkobus.compose.navigation.demo.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.adamkobus.compose.navigation.demo.ui.theme.DemoTheme

@Composable
fun PreviewComponent(content: @Composable () -> Unit) {
    DemoTheme {
        Surface(color = MaterialTheme.colors.background, content = content)
    }
}
