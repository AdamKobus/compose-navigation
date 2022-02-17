package com.adamkobus.compose.navigation.tutorial.ui.image

import android.os.SystemClock
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adamkobus.compose.navigation.ComposeNavigation
import com.adamkobus.compose.navigation.tutorial.nav.TutorialNavActions
import kotlinx.coroutines.launch

@Suppress("MagicNumber")
@Composable
fun ImageScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.ThumbUp,
            contentDescription = "app icon",
            modifier = Modifier.align(Alignment.Center)
        )

        val scope = rememberCoroutineScope()
        val textEntries = remember { mutableStateOf(emptyList<String>()) }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(textEntries.value) { item ->
                Text(text = item)
            }
        }
        Button(
            onClick = {
                scope.launch {
                    val startTime = SystemClock.elapsedRealtime()
                    val result = ComposeNavigation.getNavigationConsumer().offerBlocking(TutorialNavActions.FromImageToDialog)
                    val elapsed = SystemClock.elapsedRealtime() - startTime
                    textEntries.value += "Result $result in ${elapsed}ms"
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "Open a dialog")
        }
    }
}
