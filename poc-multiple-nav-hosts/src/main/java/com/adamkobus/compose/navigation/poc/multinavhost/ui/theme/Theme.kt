@file:Suppress("MagicNumber")

package com.adamkobus.compose.navigation.poc.multinavhost.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MainColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val YellowColorPalette = MainColorPalette.copy(background = Color(0xFFFFFBC2))
private val GreenColorPalette = MainColorPalette.copy(background = Color(0xFFC2FFC7))

@Composable
fun ComposeNavigationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = MainColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun YellowTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = YellowColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun GreenTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = GreenColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
