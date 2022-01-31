package com.adamkobus.compose.navigation.demo.ui.appbar

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Suppress("LongParameterList")
@Composable
fun AnimatedAppBar(
    appBarState: AnimatedAppBarState,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    contentPadding: PaddingValues = AppBarDefaults.ContentPadding,
    shape: Shape = RectangleShape,
    height: Dp = AppBarHeight
) {
    val targetBackground = animateColorAsState(targetValue = if (appBarState.isAnyContentAvailable) backgroundColor else Color.Transparent)
    val targetElevation = animateDpAsState(targetValue = if (appBarState.isAnyContentAvailable) elevation else 0.dp)
    Surface(
        color = targetBackground.value,
        contentColor = contentColor,
        elevation = targetElevation.value,
        shape = shape,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                contentAlignment = Alignment.CenterStart
            ) {
                AnimatedAppBarContent(appBarState)
            }
        }
    }
}

@Composable
private fun AnimatedAppBarContent(appBarState: AnimatedAppBarState) {
    val titlePosition = animateDpAsState(targetValue = if (appBarState.iconState != null) TitleIconOffset else TitleNoIconOffset)
    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.CenterStart
    ) {
        IconContent(appBarState.iconState)
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = titlePosition.value),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                TitleContent(appBarState.titleState)
            }
            ActionButtons(appBarState.actionsState, clickable = !appBarState.searchState.isSearchEnabled)
        }
    }
}

@Composable
private fun TitleContent(titleState: AppBarTitleState?) {
    ProvideTextStyle(value = MaterialTheme.typography.h6) {
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.high
        ) {
            Crossfade(targetState = titleState?.getText() ?: "", modifier = Modifier.fillMaxWidth()) { text ->
                Text(text = text, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun IconContent(iconState: AppBarIconState?) {
    val onClickWrapper: () -> Unit = {
        if (iconState != null) {
            iconState.onClick()
        }
    }
    Crossfade(targetState = iconState) { iconStateInner ->
        if (iconStateInner == null) {
            Spacer(TitleInsetWithoutIcon)
        } else {
            Row(TitleIconModifier, verticalAlignment = Alignment.CenterVertically) {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high
                ) {
                    AnimatedAppBarIcon(iconStateInner, onClick = onClickWrapper)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ActionButtons(actionsList: List<AppBarActionState>, clickable: Boolean) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            actionsList.forEachIndexed { index, item ->
                val onClickWrapper = {
                    if (clickable) {
                        item.onClick()
                    }
                }
                Crossfade(targetState = item) { itemLocal ->
                    ActionButtonContent(itemLocal, onClick = onClickWrapper)
                }
            }
        }
    }
}

@Composable
fun ActionButtonContent(item: AppBarActionState, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(imageVector = item.icon, contentDescription = stringResource(id = item.contentDescriptionResId))
    }
}

@Composable
private fun AnimatedAppBarIcon(iconState: AppBarIconState, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(iconState.icon, iconState.contentDescriptionResId?.let { stringResource(id = it) })
    }
}

val AppBarHeight = 56.dp

private val AppBarHorizontalPadding = 4.dp

private val TitleInsetWithoutIcon = Modifier
    .width(16.dp - AppBarHorizontalPadding)

private val TitleIconModifier = Modifier
    .fillMaxHeight()
    .width(72.dp - AppBarHorizontalPadding)

private val TitleNoIconOffset = 12.dp
private val TitleIconOffset = 68.dp
