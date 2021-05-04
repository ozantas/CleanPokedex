package com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeModel(
    @SerialName("name")
    val type: String
)