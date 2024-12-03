package com.adamkobus.compose.navigation.demo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adamkobus.compose.navigation.demo.ui.appbar.AppBarHeight

@Composable
fun DemoAppBackground(
    usesTopBar: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = if (usesTopBar) AppBarHeight else 0.dp),
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColorFor(backgroundColor = MaterialTheme.colorScheme.background),
        ) {
            content()
        }
    }
}
