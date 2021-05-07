package com.ozan.cleanpokedex.ui.uimodel.pokemon

data class PokemonDetailUiModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val height: PhysicalInfoUiModel,
    val weight: PhysicalInfoUiModel,
    val statList: List<PokemonStatUiModel>,
    val typeList: List<String>,
)