package com.adamkobus.compose.navigation.demo.ui.doggallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground
import com.adamkobus.compose.navigation.demo.ui.LifecycleAwareComponent
import com.adamkobus.compose.navigation.demo.ui.Paddings
import com.adamkobus.compose.navigation.demo.ui.dogs.R

@Composable
fun DogGalleryScreen(dogId: Int) {
    val vm: DogGalleryScreenVM = hiltViewModel()
    LifecycleAwareComponent(observer = vm)
    vm.dogId.bind(dogId)
    DogGalleryScreenContent(vm.interactions)
}

@Composable
private fun DogGalleryScreenContent(interactions: DogGalleryScreenInteractions) {
    DemoAppBackground(usesTopBar = true) {
        InnerContent(interactions)
    }
}

@Composable
fun InnerContent(interactions: DogGalleryScreenInteractions) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Paddings.ScreenWithTabHostInsets)
    ) {
        Button(onClick = interactions.onBackToListClicked, modifier = Modifier.align(Alignment.BottomCenter)) {
            Text(text = stringResource(id = R.string.dog_gallery_back_to_list_button))
        }
    }
}
