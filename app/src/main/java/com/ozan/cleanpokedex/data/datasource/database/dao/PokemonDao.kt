package com.ozan.cleanpokedex.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ozan.cleanpokedex.data.datasource.database.entity.PokemonListEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon LIMIT :pageSize OFFSET :offset")
    fun getPokemonList(pageSize: Int, offset: Int): List<PokemonListEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: List<PokemonListEntity>)

}