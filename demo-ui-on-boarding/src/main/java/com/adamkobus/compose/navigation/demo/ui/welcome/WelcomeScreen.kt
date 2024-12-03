package com.adamkobus.compose.navigation.demo.ui.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground
import com.adamkobus.compose.navigation.demo.ui.Paddings
import com.adamkobus.compose.navigation.demo.ui.PreviewComponent
import com.adamkobus.compose.navigation.demo.ui.onboarding.R

@Composable
fun WelcomeScreen() {
    val vm = hiltViewModel<WelcomeScreenVM>()
    WelcomeScreenContent(vm.interactions)
}

@Composable
fun WelcomeScreenContent(interactions: WelcomeScreenInteractions) {
    DemoAppBackground {
        Box(
            modifier =
            Modifier
                .padding(Paddings.Screen)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.welcome_hi),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(90.dp))
                WelcomeScreenButton(stringResource(id = R.string.welcome_button_cats), onClick = interactions.onCatsSelected)
                Spacer(modifier = Modifier.height(16.dp))
                WelcomeScreenButton(stringResource(id = R.string.welcome_button_dogs), onClick = interactions.onDogsSelected)
            }
        }
    }
}

@Composable
private fun WelcomeScreenButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(modifier = Modifier.fillMaxWidth(), onClick = onClick) {
        Text(text = text)
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    PreviewComponent {
        WelcomeScreenContent(interactions = WelcomeScreenInteractions())
    }
}
