package com.adamkobus.compose.navigation.demo.ui.topbar

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.adamkobus.compose.navigation.demo.ui.PreviewComponent

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DemoTopBar(state: TopBarState) {
    val iconState = state.iconState
    val isIconVisible = iconState.icon != null
    val background = when (state.background) {
        DemoColors.PrimarySurface -> MaterialTheme.colors.primarySurface
        DemoColors.None -> Color.Transparent
    }
    TopAppBar(
        title = {
            val titleText = getTitleTextFromState(state)
            DemoTopBarTitle(text = titleText)
        },
        navigationIcon = if (isIconVisible) {
            { DemoTopBarIcon(iconState) }
        } else {
            null
        },
        actions = {
            DemoTopBarActions(state.actions)
        },
        backgroundColor = background,
        elevation = state.elevation
    )
}

@Composable
private fun getTitleTextFromState(state: TopBarState): String? =
    state.titleResId?.let {
        stringResource(id = it)
    } ?: state.titleRaw

@Preview
@Composable
private fun DemoTopBarPreview() {
    PreviewComponent {
        DemoTopBar(TopBarState(titleRaw = "Test title").apply {
            iconState {
                icon = Icons.Filled.ArrowBack
            }
            action {
                icon = Icons.Filled.MoreVert
            }
            background = DemoColors.PrimarySurface
        })
    }
}
