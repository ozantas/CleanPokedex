package com.ozan.cleanpokedex.ui.list

import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonListUiModel

sealed class PokemonListState {

    object Loading: PokemonListState()

    data class Error(val e: PokemonListError): PokemonListState()

    data class ListUpdated(
        val list: List<PokemonListUiModel>
    ): PokemonListState()

}

