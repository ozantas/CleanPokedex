package com.ozan.cleanpokedex.ui.list

import com.ozan.cleanpokedex.R


sealed class PokemonListError(val messageResId: Int) {

    data class CannotLoad(
        val messageId: Int= R.string.error_pokemon_list_cannot_load
    ): PokemonListError(messageId)

}