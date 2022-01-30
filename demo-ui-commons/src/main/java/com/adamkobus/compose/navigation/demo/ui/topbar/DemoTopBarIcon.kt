package com.adamkobus.compose.navigation.demo.ui.topbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DemoTopBarIcon(state: TopBarIconState) {
    AnimatedContent(targetState = state.icon) {
        state.icon?.let { icon ->
            IconButton(onClick = state.onClick) {
                Icon(
                    imageVector = icon,
                    contentDescription = state.contentDescription?.let { resourceId ->
                        stringResource(id = resourceId)
                    }
                )
            }
        }
    }
}
