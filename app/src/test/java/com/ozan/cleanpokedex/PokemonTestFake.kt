package com.ozan.cleanpokedex

import com.ozan.cleanpokedex.data.datasource.database.entity.PokemonListEntity
import com.ozan.cleanpokedex.data.datasource.network.model.pokemonlist.PokemonListItemModel

object PokemonTestFake {

    val imageBaseUrl= "https://pokeres.bastionbot.org/images/pokemon/"

    fun createEntityList(size: Int= 50): List<PokemonListEntity> {
        return (1..size).map {
            PokemonListEntity(it, "pokemon${it}")
        }
    }

    fun createModelList(size: Int= 50): List<PokemonListItemModel> {
        return (1..size).map {
            PokemonListItemModel(it.toString(), "https://pokeapi.co/api/v2/pokemon/${it}/")
        }
    }

}