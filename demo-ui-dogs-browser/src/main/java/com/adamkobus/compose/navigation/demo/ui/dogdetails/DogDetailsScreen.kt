package com.adamkobus.compose.navigation.demo.ui.dogdetails

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
import com.adamkobus.compose.navigation.democore.util.AsyncData

@Composable
fun DogDetailsScreen(dogId: Int) {
    val vm = hiltViewModel<DogDetailsScreenVM>()
    LifecycleAwareComponent(observer = vm)
    vm.dogIdParam.bind(dogId)
    DogDetailsScreenContent(vm.screenState, vm.interactions)
}

@Composable
private fun DogDetailsScreenContent(
    screenState: DogDetailsScreenState,
    interactions: DogDetailsScreenInteractions,
) {
    DemoAppBackground(usesTopBar = true) {
        when (val dogInfo = screenState.dogInfo.value) {
            is AsyncData.Loading -> LoadingScreen()
            is AsyncData.Present -> DogDetails(dogInfo.data, interactions)
            is AsyncData.Missing -> DogError()
        }
    }
}

@Composable
private fun DogDetails(
    dogInfo: DogInfo,
    interactions: DogDetailsScreenInteractions,
) {
    Column(modifier = Modifier.padding(Paddings.Screen)) {
        Text(text = dogInfo.name, style = MaterialTheme.typography.titleMedium)
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
        modifier =
        Modifier
            .fillMaxSize()
            .padding(Paddings.Screen),
    ) {
        Text(text = stringResource(id = R.string.dog_details_error), style = MaterialTheme.typography.titleMedium, color = Color.Red)
    }
}
