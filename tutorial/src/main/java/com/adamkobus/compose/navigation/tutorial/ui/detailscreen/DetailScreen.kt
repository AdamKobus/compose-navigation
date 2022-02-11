package com.adamkobus.compose.navigation.tutorial.ui.detailscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.adamkobus.compose.navigation.NavigationConsumer
import com.adamkobus.compose.navigation.tutorial.nav.TutorialNavActions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun DetailScreen(itemId: Int) {
    val vm: DetailScreenVM = hiltViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(onClick = vm.interactions.onBackClicked) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
        }
        Text(text = "Opened item with id $itemId", modifier = Modifier.align(Alignment.Center))
        Button(
            onClick = { vm.interactions.onOpenDialogClicked(itemId) },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "Open a dialog")
        }
    }
}

@HiltViewModel
class DetailScreenVM @Inject constructor(
    private val navigationConsumer: NavigationConsumer
) : ViewModel() {
    val interactions = DetailScreenInteractions(
        onBackClicked = {
            navigationConsumer.offer(TutorialNavActions.FromDetailNavigateBack)
        },
        onOpenDialogClicked = { itemId ->
            navigationConsumer.offer(TutorialNavActions.fromDetailToDialog(itemId))
        }
    )
}

data class DetailScreenInteractions(
    val onBackClicked: () -> Unit,
    val onOpenDialogClicked: (Int) -> Unit
)

@Composable
fun DetailScreenDialog(itemId: Int) {
    Card {
        Text(text = "Item id: $itemId", modifier = Modifier.padding(24.dp))
    }
}
