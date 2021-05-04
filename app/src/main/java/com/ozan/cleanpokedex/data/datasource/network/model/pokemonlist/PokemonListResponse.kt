package com.ozan.cleanpokedex.data.datasource.network.model.pokemonlist


import com.ozan.cleanpokedex.data.datasource.network.BaseResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    @SerialName("results")
    val pokemonListItems: List<PokemonListItemModel>
): BaseResponse()