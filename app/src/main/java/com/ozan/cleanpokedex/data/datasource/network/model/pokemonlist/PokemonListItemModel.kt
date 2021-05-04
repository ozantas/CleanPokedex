package com.ozan.cleanpokedex.data.datasource.network.model.pokemonlist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListItemModel(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)