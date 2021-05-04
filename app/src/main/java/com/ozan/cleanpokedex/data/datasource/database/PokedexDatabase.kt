package com.ozan.cleanpokedex.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ozan.cleanpokedex.data.datasource.database.dao.PokemonDao
import com.ozan.cleanpokedex.data.datasource.database.entity.PokemonListEntity

@Database(
    entities = [
        PokemonListEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}