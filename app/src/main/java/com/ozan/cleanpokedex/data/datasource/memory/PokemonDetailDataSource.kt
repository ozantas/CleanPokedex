package com.ozan.cleanpokedex.data.datasource.memory

import com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail.PokemonDetailResponse

class PokemonDetailDataSource {

    companion object {
        private var cache: MutableMap<String, PokemonDetailResponse> = mutableMapOf()
    }

    fun getDetail(name: String): PokemonDetailResponse? {
        return cache[name]
    }

    fun save(name: String, detailResponse: PokemonDetailResponse) {
        cache[name] = detailResponse
    }

}