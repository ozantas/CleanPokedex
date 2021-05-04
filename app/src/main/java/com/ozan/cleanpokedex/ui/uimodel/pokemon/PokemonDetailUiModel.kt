package com.ozan.cleanpokedex.ui.uimodel.pokemon

data class PokemonDetailUiModel(
    val name: String,
    val imageUrl: String,
    val height: String,
    val weight: String,
    val statList: List<PokemonStatUiModel>,
    val type: String
)