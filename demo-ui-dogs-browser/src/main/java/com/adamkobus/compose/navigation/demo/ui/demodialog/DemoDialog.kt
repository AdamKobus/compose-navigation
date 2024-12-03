package com.adamkobus.compose.navigation.demo.ui.demodialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adamkobus.compose.navigation.demo.ui.Paddings
import com.adamkobus.compose.navigation.demo.ui.dogs.R
import com.adamkobus.compose.navigation.demo.ui.theme.DemoTheme

@Composable
fun DemoDialog() {
    val vm = hiltViewModel<DemoDialogVM>()
    DemoDialogContent(vm.interactions.onCancelClicked)
}

@Composable
private fun DemoDialogContent(onCancelClicked: () -> Unit) {
    Box(
        modifier = Modifier.padding(vertical = Paddings.Screen),
        contentAlignment = Alignment.Center,
    ) {
        Card(modifier = Modifier.widthIn(0.dp, 350.dp)) {
            DialogCardContent(onCancelClicked)
        }
    }
}

@Composable
private fun DialogCardContent(onCancelClicked: () -> Unit) {
    Column(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(Paddings.Screen),
    ) {
        Text(text = stringResource(id = R.string.demo_dialog_text))
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onCancelClicked, modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.demo_dialog_close))
        }
    }
}

@Preview
@Composable
private fun DemoDialogContentPreview() {
    DemoTheme {
        DemoDialogContent(onCancelClicked = {})
    }
}
