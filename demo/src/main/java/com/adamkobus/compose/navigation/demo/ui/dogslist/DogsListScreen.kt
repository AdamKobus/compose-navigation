package com.adamkobus.compose.navigation.demo.ui.dogslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
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
    val vm = hiltViewModel<DogsListVM>()
    LifecycleAwareComponent(observer = vm)
    DemoAppBackground(usesTopBar = true) {
        DogsListScreenContent(vm.listState.value, vm.interactions)
    }
}

@Composable
private fun DogsListScreenContent(state: DogsListState, interactions: DogsListInteractions) {
    when (state) {
        is DogsListState.Loading -> LoadingScreen()
        is DogsListState.Loaded -> DogsListContent(state.data, interactions.onDogsItemSelected)
    }
}

@Composable
private fun DogsListContent(data: List<DogInfo>, onDogsItemSelected: (DogInfo) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(data, key = { it.id }) { dogInfo ->
            DogListItem(dogInfo = dogInfo, onDogsItemSelected)
        }
    }
}

@Composable
private fun DogListItem(dogInfo: DogInfo, onDogsItemSelected: (DogInfo) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Paddings.Screen)
            .clickable {
                onDogsItemSelected(dogInfo)
            },
        elevation = Elevation.AppBar
    ) {
        Column(modifier = Modifier.padding(Paddings.CardPadding)) {
            Text(text = dogInfo.name)
        }
    }
}
