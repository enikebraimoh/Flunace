package com.enike.flunace.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Orange800,
    primaryVariant = Orange900,
    onPrimary = Color.White,
    secondary = Orange800,
    secondaryVariant = Orange900,
    onSecondary = Color.White,
    error = Orange700
)

val Colors.myColor: Color
    get() = if (!isLight) Color(0xFF424242) else Color(0xFFF2F3F2)

private val LightColorPalette = lightColors(
    primary = Orange800,
    primaryVariant = Orange900,
    secondary = Orange800,
    onPrimary = Color.White,
    onSecondary = Color.White,
    secondaryVariant = Orange900,
    error = Orange700

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun FlunaceTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
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