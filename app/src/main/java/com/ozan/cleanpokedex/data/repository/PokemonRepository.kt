package com.ozan.cleanpokedex.data.repository

import com.ozan.cleanpokedex.data.Resource
import com.ozan.cleanpokedex.data.datasource.database.entity.PokemonListEntity
import com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail.PokemonDetailResponse
import com.ozan.cleanpokedex.data.datasource.network.model.pokemonlist.PokemonListItemModel

interface PokemonRepository {

    suspend fun getPokemonList(): Resource<List<PokemonListEntity>>
    suspend fun getPokemonDetail(name: String): Resource<PokemonDetailResponse>
}