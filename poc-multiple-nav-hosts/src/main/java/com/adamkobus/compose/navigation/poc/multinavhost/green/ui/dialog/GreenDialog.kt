package com.adamkobus.compose.navigation.poc.multinavhost.green.ui.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.poc.multinavhost.ui.theme.Paddings

@Composable
fun GreenDialog() {
    val vm: GreenDialogVM = hiltViewModel()

    GreenDialogContent(vm.interactions)
}

@Composable
private fun GreenDialogContent(
    interactions: GreenDialogInteractions
) {
    Card(backgroundColor = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(Paddings.Screen), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Green dialog")
            Spacer(modifier = Modifier.height(45.dp))
            Button(onClick = interactions.onDismissClicked) {
                Text(text = "Dismiss")
            }
        }
    }
}
