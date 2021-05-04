package com.ozan.cleanpokedex.data.datasource.network

import com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail.PokemonDetailResponse
import com.ozan.cleanpokedex.data.datasource.network.model.pokemonlist.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApi {

  @GET("pokemon")
  suspend fun getPokemonList(
    @Query("limit")
    limit: Int,
    @Query("offset")
    offset: Int
  ): PokemonListResponse

  @GET("pokemon/{name}")
  suspend fun getPokemonDetail(@Path("name") name: String): PokemonDetailResponse

}