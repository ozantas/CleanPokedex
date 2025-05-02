package com.ozan.cleanpokedex.extension

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.ozan.cleanpokedex.ui.theme.PokedexTheme

inline fun ComponentActivity.ui(
    crossinline content: @Composable () -> Unit
) {
    setContent {
        PokedexTheme {
            content()
        }
    }
}