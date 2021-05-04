package com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    @SerialName("height")
    val height: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("order")
    val order: Int,
    @SerialName("stats")
    val stats: List<StatListItem>,
    @SerialName("types")
    val types: List<TypeListItem>,
    @SerialName("weight")
    val weight: Int
)