package com.adamkobus.compose.navigation.demo.settings.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.settings.R
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground
import com.adamkobus.compose.navigation.demo.ui.LifecycleAwareComponent
import com.adamkobus.compose.navigation.demo.ui.Paddings

@Composable
internal fun HomeScreen() {
    DemoAppBackground(usesTopBar = true) {
        val vm = hiltViewModel<HomeScreenVM>()
        LifecycleAwareComponent(observer = vm)
        HomeScreenContent()
    }
}

@Composable
fun HomeScreenContent() {
    Box(modifier = Modifier.fillMaxSize().padding(Paddings.Screen)) {
        Text(text = stringResource(id = R.string.settings_title))
    }
}
