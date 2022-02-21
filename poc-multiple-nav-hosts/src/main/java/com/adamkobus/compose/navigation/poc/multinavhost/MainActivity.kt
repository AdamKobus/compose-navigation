package com.adamkobus.compose.navigation.poc.multinavhost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.adamkobus.compose.navigation.poc.multinavhost.main.nav.MainNavHost
import com.adamkobus.compose.navigation.poc.multinavhost.ui.theme.ComposeNavigationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTheme {
                MainNavHost()
            }
        }
    }
}
