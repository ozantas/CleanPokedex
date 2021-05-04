package com.ozan.cleanpokedex.data.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonListEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)