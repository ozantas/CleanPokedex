package com.ozan.cleanpokedex.ui.detail

import com.ozan.cleanpokedex.R


sealed class PokemonDetailError(val messageResId: Int) {

    data class CannotLoad(
        val messageId: Int= R.string.error_pokemon_detail_cannot_load
    ): PokemonDetailError(messageId)

}