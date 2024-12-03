package com.adamkobus.compose.navigation.demo.devmenu.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val DevMenuColors =
    darkColorScheme(
        primary = Color.DarkGray,
        primaryContainer = Color.DarkGray,
        onPrimary = Color.White,
        secondary = Color.DarkGray,
        onSecondary = Color.White,
        surface = Color.DarkGray,
        onSurface = Color.White,
        background = Color.DarkGray,
        onBackground = Color.White,
    )

private val Shapes =
    Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp),
    )

private val Typography =
    Typography(
        bodyLarge =
        TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),
        titleMedium =
        TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
        ),
    )

@Composable
internal fun DevMenuTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DevMenuColors,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
