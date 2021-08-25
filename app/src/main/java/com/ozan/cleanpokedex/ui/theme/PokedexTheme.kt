package com.ozan.cleanpokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = blue500,
    primaryVariant = blue800,
    secondary = blue500,
    secondaryVariant = teal,
    background = gray200,
    surface = Color.White,
    error = red500,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = gray900,
    onSurface = gray800,
    onError = red500
)

private val DarkThemeColors = darkColors(
    primary = gray800,
    primaryVariant = gray700,
    secondary = blue700,
    secondaryVariant = teal,
    background = Color.Black,
    surface = gray900,
    error = red500,
    onPrimary = gray300,
    onSecondary = gray300,
    onBackground = gray200,
    onSurface = gray300,
    onError = red300
)

val Colors.onBackgroundVariant: Color
    get() = if (isLight) gray700 else gray500

val Colors.surfaceVariant: Color
    get() = if (isLight) gray100 else gray800

val Colors.starColor: Color
    get() = if (isLight) yellow700 else yellow400

val Colors.disabledColor: Color
    get() = if (isLight) gray400 else gray700

@Composable
fun PokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = PokedexTypography,
        shapes = PokedexShapes,
        content = content
    )
}
