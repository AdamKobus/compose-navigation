package com.adamkobus.compose.navigation.poc.multinavhost.yellow.ui.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.poc.multinavhost.ui.theme.Paddings

@Composable
fun YellowDialog() {
    val vm: YellowDialogVM = hiltViewModel()

    YellowDialogContent(vm.interactions)
}

@Composable
private fun YellowDialogContent(interactions: YellowDialogInteractions) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)) {
        Column(modifier = Modifier.padding(Paddings.Screen), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Yellow dialog")
            Spacer(modifier = Modifier.height(45.dp))
            Button(onClick = interactions.onDismissClicked) {
                Text(text = "Dismiss")
            }
        }
    }
}
