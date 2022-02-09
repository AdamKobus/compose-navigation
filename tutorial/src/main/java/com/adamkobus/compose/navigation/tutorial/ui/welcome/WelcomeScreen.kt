package com.adamkobus.compose.navigation.tutorial.ui.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.tutorial.nav.TutorialNavActions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun WelcomeScreen() {
    val vm: WelcomeScreenVM = hiltViewModel()
    WelcomeScreenContent(interactions = vm.interactions)
}

@Composable
private fun WelcomeScreenContent(interactions: WelcomeScreenInteractions = WelcomeScreenInteractions.STUB) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = interactions.onShowImageClicked, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Show me an image!")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = interactions.onShowListClicked, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Show me a list!")
        }
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreenContent()
}

@HiltViewModel
class WelcomeScreenVM @Inject constructor(
    val navigationConsumer: NavigationConsumer
) : ViewModel() {
    val interactions = WelcomeScreenInteractions(
        onShowImageClicked = {
            navigationConsumer.offer(TutorialNavActions.FromWelcomeToImage)
        },
        onShowListClicked = {
            navigationConsumer.offer(TutorialNavActions.FromWelcomeToList)
        },
    )
}

data class WelcomeScreenInteractions(
    val onShowImageClicked: () -> Unit,
    val onShowListClicked: () -> Unit
) {
    companion object {
        val STUB = WelcomeScreenInteractions(
            onShowImageClicked = {},
            onShowListClicked = {}
        )
    }
}
