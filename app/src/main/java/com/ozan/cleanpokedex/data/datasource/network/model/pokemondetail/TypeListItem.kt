package com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeListItem(
    @SerialName("type")
    val type: TypeModel
)