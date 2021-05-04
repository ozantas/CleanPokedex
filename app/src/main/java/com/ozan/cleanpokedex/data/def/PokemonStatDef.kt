package com.ozan.cleanpokedex.data.def

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class PokemonStatDef( val statName: String) {
    @SerialName("hp")
    HP ("HP"),
    @SerialName("attack")
    ATTACK("ATK"),
    @SerialName("defense")
    DEFENSE("DEF"),
    @SerialName("special-attack")
    SP_ATTACK("SP ATK"),
    @SerialName("special-defense")
    SP_DEFENSE("SP DEF"),
    @SerialName("speed")
    SPEED("SPD")
}