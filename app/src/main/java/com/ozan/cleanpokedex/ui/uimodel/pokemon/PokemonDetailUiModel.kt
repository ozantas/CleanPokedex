package com.ozan.cleanpokedex.ui.uimodel.pokemon

import com.ozan.cleanpokedex.ui.util.PokemonType

data class PokemonDetailUiModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val height: PhysicalInfoUiModel,
    val weight: PhysicalInfoUiModel,
    val statList: List<PokemonStatUiModel>,
    val typeList: List<PokemonType>,
)