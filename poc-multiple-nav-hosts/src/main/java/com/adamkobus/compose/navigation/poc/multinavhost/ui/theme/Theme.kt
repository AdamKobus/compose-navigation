@file:Suppress("MagicNumber")

package com.adamkobus.compose.navigation.poc.multinavhost.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MainColorPalette =
    lightColorScheme(
        primary = Purple500,
        secondary = Teal200,
        background = Color.White,
        surface = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
    )

private val YellowColorPalette = MainColorPalette.copy(background = Color(0xFFFFFBC2))
private val GreenColorPalette = MainColorPalette.copy(background = Color(0xFFC2FFC7))

@Composable
fun ComposeNavigationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MainColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}

@Composable
fun YellowTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = YellowColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}

@Composable
fun GreenTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = GreenColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
