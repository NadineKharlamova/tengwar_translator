package com.tengwar.translator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Gold = Color(0xFFC9A227)
private val DeepBlue = Color(0xFF1F2A44)
private val Parchment = Color(0xFFF6F1E4)

private val LightColors = lightColorScheme(
    primary = DeepBlue,
    onPrimary = Parchment,
    secondary = Gold,
    onSecondary = DeepBlue,
    background = Parchment,
    surface = Color(0xFFFFFDF7),
    onSurface = Color(0xFF1B1B1B)
)

private val DarkColors = darkColorScheme(
    primary = Gold,
    onPrimary = DeepBlue,
    secondary = Gold,
    background = Color(0xFF12151F),
    surface = Color(0xFF1B2030),
    onSurface = Color(0xFFEDEDED)
)

@Composable
fun TengwarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography(),
        content = content
    )
}
