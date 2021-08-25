package com.ozan.cleanpokedex.ui.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import com.ozan.cleanpokedex.R

@Composable
fun PokemonImage(modifier: Modifier, url: String, desc: String = "") {
    Image(
        painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
                placeholder(R.drawable.ic_pokeball)
                error(R.drawable.ic_pokeball)
            }
        ),
        contentDescription = desc,
        modifier = modifier
    )

}