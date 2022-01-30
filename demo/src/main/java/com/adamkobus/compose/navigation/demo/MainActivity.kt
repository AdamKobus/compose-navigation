package com.adamkobus.compose.navigation.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.adamkobus.compose.navigation.demo.ui.app.DemoAppRoot
import com.adamkobus.compose.navigation.demo.ui.theme.DemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoTheme {
                DemoAppRoot()
            }
        }
    }
}
