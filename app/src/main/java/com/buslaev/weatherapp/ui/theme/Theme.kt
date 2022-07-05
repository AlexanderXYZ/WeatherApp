package com.buslaev.weatherapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Blue,
    onPrimary = DarkBlue,
    secondary = LightBlue,
    background = Color.White,
    onBackground = Black,
    onSurface = LightGrey,
)

private val LightColorPalette = lightColors(
    primary = Blue,
    onPrimary = DarkBlue,
    secondary = LightBlue,
    background = Color.White,
    onBackground = Black,
    onSurface = LightGrey,
)

@Composable
fun WeatherAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}