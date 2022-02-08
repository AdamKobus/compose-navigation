package com.adamkobus.compose.navigation.demo.ui.catslist

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
import com.adamkobus.compose.navigation.demo.ui.LifecycleAwareComponent
import com.adamkobus.compose.navigation.demo.ui.Paddings
import com.adamkobus.compose.navigation.demo.ui.loading.LoadingScreen
import com.adamkobus.compose.navigation.democore.data.CatInfo

@Composable
fun CatsListScreen() {
    val vm = hiltViewModel<CatsListVM>()
    LifecycleAwareComponent(observer = vm)
    DemoAppBackground(usesTopBar = true) {
        CatsListScreenContent(vm.listState.value, vm.interactions)
    }
}

@Composable
private fun CatsListScreenContent(state: CatsListState, interactions: CatsListInteractions) {
    when (state) {
        is CatsListState.Loading -> LoadingScreen()
        is CatsListState.Loaded -> CatsListContent(state.data, interactions.onCatListItemSelected)
    }
}

@Composable
private fun CatsListContent(data: List<CatInfo>, onCatListItemSelected: (CatInfo) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(data, key = { it.id }) { catInfo ->
            CatListItem(catInfo = catInfo, onCatListItemSelected)
        }
    }
}

@Composable
private fun CatListItem(catInfo: CatInfo, onCatListItemSelected: (CatInfo) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Paddings.Screen)
            .clickable {
                onCatListItemSelected(catInfo)
            },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(Paddings.CardPadding)) {
            Text(text = catInfo.name)
        }
    }
}