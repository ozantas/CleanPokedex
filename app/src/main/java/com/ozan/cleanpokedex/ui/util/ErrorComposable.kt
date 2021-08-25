package com.ozan.cleanpokedex.ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ozan.cleanpokedex.ui.theme.PokedexTheme

@Composable
fun CenteredError(message: String) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(message)
    }
}

@Preview(showBackground = true)
@Composable
fun CenteredErrorPreview() {
    PokedexTheme(darkTheme = false) {
        CenteredError(message = "error message")
    }

}