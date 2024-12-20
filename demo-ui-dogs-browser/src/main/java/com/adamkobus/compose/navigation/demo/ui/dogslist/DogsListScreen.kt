package com.adamkobus.compose.navigation.demo.ui.dogslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.ui.DemoAppBackground
import com.adamkobus.compose.navigation.demo.ui.Elevation
import com.adamkobus.compose.navigation.demo.ui.LifecycleAwareComponent
import com.adamkobus.compose.navigation.demo.ui.Paddings
import com.adamkobus.compose.navigation.demo.ui.loading.LoadingScreen
import com.adamkobus.compose.navigation.democore.data.DogInfo

@Composable
fun DogsListScreen() {
    val vm = hiltViewModel<DogsListScreenVM>()
    LifecycleAwareComponent(observer = vm)
    DemoAppBackground(usesTopBar = true) {
        DogsListScreenContent(vm.screenState, vm.interactions)
    }
}

@Composable
private fun DogsListScreenContent(
    state: DogsListScreenState,
    interactions: DogsListInteractions,
) {
    when (state.isLoading.value) {
        true -> LoadingScreen()
        false -> DogsListContent(state, interactions.onDogsItemSelected)
    }
}

@Composable
private fun DogsListContent(
    state: DogsListScreenState,
    onDogsItemSelected: (DogInfo) -> Unit,
) {
    val data = state.dogsList.value
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = Paddings.ScreenWithTabHostInsets,
    ) {
        items(data, key = { it.id }) { dogInfo ->
            DogListItem(dogInfo = dogInfo, onDogsItemSelected)
        }
    }
}

@Composable
private fun DogListItem(
    dogInfo: DogInfo,
    onDogsItemSelected: (DogInfo) -> Unit,
) {
    Card(
        modifier =
        Modifier
            .fillMaxWidth()
            .clickable {
                onDogsItemSelected(dogInfo)
            },
        elevation = CardDefaults.elevatedCardElevation(Elevation.AppBar),
    ) {
        Column(modifier = Modifier.padding(Paddings.CardPadding)) {
            Text(text = dogInfo.name)
        }
    }
}
