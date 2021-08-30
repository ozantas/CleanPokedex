package com.ozan.cleanpokedex.ui.detail

import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonDetailUiModel

sealed class PokemonDetailState {
    object Loading : PokemonDetailState()

    data class Error(
        val e: PokemonDetailError
    ) : PokemonDetailState()

    data class Success(
        val detail: PokemonDetailUiModel
    ) : PokemonDetailState()
}
