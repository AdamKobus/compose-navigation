package com.adamkobus.compose.navigation.poc.multinavhost.main.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.poc.multinavhost.R
import com.adamkobus.compose.navigation.poc.multinavhost.ui.common.ScreenBackground
import com.adamkobus.compose.navigation.poc.multinavhost.ui.theme.Paddings

@Composable
fun HomeScreen() {
    val vm: HomeScreenVM = hiltViewModel()

    HomeScreenContent(vm.interactions)
}

@Composable
fun HomeScreenContent(interactions: HomeScreenInteractions) {
    ScreenBackground {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Paddings.Screen)
        ) {
            Text(
                text = stringResource(id = R.string.home_screen_description),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(45.dp))
            Button(onClick = interactions.onContinueClicked, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.home_screen_continue_button))
            }
        }
    }
}
