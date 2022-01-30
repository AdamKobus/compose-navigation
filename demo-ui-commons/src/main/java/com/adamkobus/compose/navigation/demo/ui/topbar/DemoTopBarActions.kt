package com.adamkobus.compose.navigation.demo.ui.topbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun DemoTopBarActions(actions: MutableList<TopBarAction>) {
    actions.forEach { action ->
        action.icon?.let { vector ->
            IconButton(onClick = action.onClick) {
                Icon(imageVector = vector, contentDescription = action.contentDescription?.let { stringResource(id = it) })
            }
        }
    }
}
