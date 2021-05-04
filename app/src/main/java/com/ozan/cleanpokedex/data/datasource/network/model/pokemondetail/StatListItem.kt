package com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatListItem(
    @SerialName("base_stat")
    val baseStat: Int,
    @SerialName("stat")
    val stat: StatModel
)