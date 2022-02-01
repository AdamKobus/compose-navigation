package com.adamkobus.compose.navigation.demo.ui.dogdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.R
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground
import com.adamkobus.compose.navigation.demo.ui.LifecycleAwareComponent
import com.adamkobus.compose.navigation.demo.ui.Paddings
import com.adamkobus.compose.navigation.demo.ui.loading.LoadingScreen
import com.adamkobus.compose.navigation.democore.data.DogInfo

@Composable
fun DogDetailsScreen(dogId: Int) {
    val vm = hiltViewModel<DogDetailsScreenVM>()
    vm.bindDogId(dogId)
    LifecycleAwareComponent(observer = vm)
    val screenState = vm.screenState.value
    DogDetailsScreenContent(screenState)
}

@Composable
fun DogDetailsScreenContent(screenState: DogDetailsState) {
    DemoAppBackground(usesTopBar = true) {
        when (screenState) {
            is DogDetailsState.Loading -> LoadingScreen()
            is DogDetailsState.Loaded -> DogDetails(screenState.dogInfo)
            is DogDetailsState.Error -> DogError()
        }
    }
}

@Composable
private fun DogDetails(dogInfo: DogInfo) {
    Box(modifier = Modifier.padding(Paddings.Screen)) {
        Text(text = dogInfo.name, style = MaterialTheme.typography.h2)
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
