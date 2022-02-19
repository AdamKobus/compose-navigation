package com.adamkobus.compose.navigation.demo.ui.dogdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground
import com.adamkobus.compose.navigation.demo.ui.LifecycleAwareComponent
import com.adamkobus.compose.navigation.demo.ui.Paddings
import com.adamkobus.compose.navigation.demo.ui.dogs.R
import com.adamkobus.compose.navigation.demo.ui.loading.LoadingScreen
import com.adamkobus.compose.navigation.democore.data.DogInfo

@Composable
fun DogDetailsScreen(dogId: Int) {
    val vm = hiltViewModel<DogDetailsScreenVM>()
    LifecycleAwareComponent(observer = vm)
    vm.dogId.bind(dogId)
    val screenState = vm.screenState.value
    val interactions = vm.interactions
    DogDetailsScreenContent(screenState, interactions)
}

@Composable
private fun DogDetailsScreenContent(screenState: DogDetailsState, interactions: DogDetailsScreenInteractions) {
    DemoAppBackground(usesTopBar = true) {
        when (screenState) {
            is DogDetailsState.Loading -> LoadingScreen()
            is DogDetailsState.Loaded -> DogDetails(screenState.dogInfo, interactions)
            is DogDetailsState.Error -> DogError()
        }
    }
}

@Composable
private fun DogDetails(dogInfo: DogInfo, interactions: DogDetailsScreenInteractions) {
    Column(modifier = Modifier.padding(Paddings.Screen)) {
        Text(text = dogInfo.name, style = MaterialTheme.typography.h2)
        Spacer(modifier = Modifier.height(60.dp))
        Button(onClick = interactions.onOpenDialogClicked, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.dog_details_open_dialog_button))
        }
        Spacer(modifier = Modifier.height(60.dp))
        Button(onClick = interactions.onOpenGalleryClicked, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.dog_details_open_gallery_button))
        }
    }
}

@Composable
private fun DogError() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Paddings.Screen)
    ) {
        Text(text = stringResource(id = R.string.dog_details_error), style = MaterialTheme.typography.h2, color = Color.Red)
    }
}
