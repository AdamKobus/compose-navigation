@file:Suppress("MagicNumber")

package com.adamkobus.compose.navigation.tutorial.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.tutorial.nav.TutorialNavActions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun ListScreen() {
    val vm: ListScreenVM = hiltViewModel()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(vm.listContent.value, key = { it.id }) { itemData ->
            ListElement(itemData, vm.interactions.onItemSelected)
        }
    }
}

@Composable
private fun ListElement(data: ListItemData, onItemClicked: (ListItemData) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClicked(data) }
    ) {
        Text(text = data.title, modifier = Modifier.padding(14.dp))
    }
}

@HiltViewModel
class ListScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer
) : ViewModel() {
    val listContent = mutableStateOf(
        // will generate 50 list elements numbered from 1 to 50
        generateSequence(1) { it + 1 }.take(50).map { ListItemData(it, "List item #$it") }.toList()
    )
    val interactions = ListScreenInteractions(
        onItemSelected = {
            navigationConsumer.offer(TutorialNavActions.fromListToDetail(it.id))
        }
    )
}

data class ListScreenInteractions(
    val onItemSelected: (ListItemData) -> Unit
)

data class ListItemData(
    val id: Int,
    val title: String
)
