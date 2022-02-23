package com.adamkobus.compose.navigation.demo.settings.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground
import com.adamkobus.compose.navigation.demo.ui.LifecycleAwareComponent
import com.adamkobus.compose.navigation.demo.ui.Paddings
import com.adamkobus.compose.navigation.demo.ui.settings.R

@Composable
internal fun SettingsHomeScreen() {
    DemoAppBackground(usesTopBar = true) {
        val vm = hiltViewModel<HomeScreenVM>()
        LifecycleAwareComponent(observer = vm)
        HomeScreenContent()
    }
}

@Composable
fun HomeScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Paddings.Screen)
    ) {
        Text(text = stringResource(id = R.string.settings_title), style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = stringResource(id = R.string.settings_dev_menu_info))
    }
}
