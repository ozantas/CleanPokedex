package com.ozan.cleanpokedex.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.ozan.cleanpokedex.R

@Composable
fun PokemonImage(modifier: Modifier, url: String, desc: String = "") {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = desc,
        contentScale = ContentScale.Fit,
        placeholder = painterResource(R.drawable.ic_pokeball),
        error = painterResource(R.drawable.ic_pokeball),
    )
}