package com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail


import com.ozan.cleanpokedex.data.def.PokemonStatDef
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatModel(
    @SerialName("name")
    val name: PokemonStatDef
)