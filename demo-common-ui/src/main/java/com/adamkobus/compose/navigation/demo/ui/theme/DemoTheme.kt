@file:Suppress("MagicNumber")

package com.adamkobus.compose.navigation.demo.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Teal200 = Color(0xFF03DAC5)

private val Primary = Color(0xFF35B6B6)
private val PrimaryVariant = Color(0xFF5CD1D1)
private val Background = Color(0xFFEEEEEE)

private val DemoPalette = lightColors(
    primary = Primary,
    primaryVariant = PrimaryVariant,
    onPrimary = Color.Black,
    secondary = Teal200,
    surface = Color.White,
    background = Background
)

@Composable
fun DemoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DemoPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
