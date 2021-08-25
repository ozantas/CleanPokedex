package com.ozan.cleanpokedex

import com.ozan.cleanpokedex.data.datasource.database.entity.PokemonListEntity
import com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail.*
import com.ozan.cleanpokedex.data.datasource.network.model.pokemonlist.PokemonListItemModel
import com.ozan.cleanpokedex.data.datasource.network.model.pokemonlist.PokemonListResponse
import com.ozan.cleanpokedex.data.def.PokemonStatDef
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object PokemonTestFake {


    val page1Json = "{\"count\":1118,\"next\":\"https://pokeapi.co/api/v2/pokemon/?offset=10&limit=10\",\"previous\":null,\"results\":[{\"name\":\"bulbasaur\",\"url\":\"https://pokeapi.co/api/v2/pokemon/1/\"},{\"name\":\"ivysaur\",\"url\":\"https://pokeapi.co/api/v2/pokemon/2/\"},{\"name\":\"venusaur\",\"url\":\"https://pokeapi.co/api/v2/pokemon/3/\"},{\"name\":\"charmander\",\"url\":\"https://pokeapi.co/api/v2/pokemon/4/\"},{\"name\":\"charmeleon\",\"url\":\"https://pokeapi.co/api/v2/pokemon/5/\"},{\"name\":\"charizard\",\"url\":\"https://pokeapi.co/api/v2/pokemon/6/\"},{\"name\":\"squirtle\",\"url\":\"https://pokeapi.co/api/v2/pokemon/7/\"},{\"name\":\"wartortle\",\"url\":\"https://pokeapi.co/api/v2/pokemon/8/\"},{\"name\":\"blastoise\",\"url\":\"https://pokeapi.co/api/v2/pokemon/9/\"},{\"name\":\"caterpie\",\"url\":\"https://pokeapi.co/api/v2/pokemon/10/\"}]}"
    val page2Json= "{\"count\":1118,\"next\":\"https://pokeapi.co/api/v2/pokemon/?offset=20&limit=10\",\"previous\":\"https://pokeapi.co/api/v2/pokemon/?offset=0&limit=10\",\"results\":[{\"name\":\"metapod\",\"url\":\"https://pokeapi.co/api/v2/pokemon/11/\"},{\"name\":\"butterfree\",\"url\":\"https://pokeapi.co/api/v2/pokemon/12/\"},{\"name\":\"weedle\",\"url\":\"https://pokeapi.co/api/v2/pokemon/13/\"},{\"name\":\"kakuna\",\"url\":\"https://pokeapi.co/api/v2/pokemon/14/\"},{\"name\":\"beedrill\",\"url\":\"https://pokeapi.co/api/v2/pokemon/15/\"},{\"name\":\"pidgey\",\"url\":\"https://pokeapi.co/api/v2/pokemon/16/\"},{\"name\":\"pidgeotto\",\"url\":\"https://pokeapi.co/api/v2/pokemon/17/\"},{\"name\":\"pidgeot\",\"url\":\"https://pokeapi.co/api/v2/pokemon/18/\"},{\"name\":\"rattata\",\"url\":\"https://pokeapi.co/api/v2/pokemon/19/\"},{\"name\":\"raticate\",\"url\":\"https://pokeapi.co/api/v2/pokemon/20/\"}]}"

    val detailResponse = PokemonDetailResponse(
        id= 3,
        height = 100,
        weight = 100,
        name = "Charizard",
        stats = listOf(
            StatListItem(150, StatModel(PokemonStatDef.ATTACK)),
            StatListItem(100, StatModel(PokemonStatDef.DEFENSE))
        ),
        types = listOf(
            TypeListItem(TypeModel("fire"))
        )
    )

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