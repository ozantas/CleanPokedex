package com.ozan.cleanpokedex.ui.preview

import com.ozan.cleanpokedex.ui.uimodel.pokemon.PhysicalInfoUiModel
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonDetailUiModel
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonListUiModel
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonStatUiModel

object PokemonPreview {
    val pokemon1 = PokemonListUiModel(
        1,
        "Charizard",
        "img1"
    )
    val pokemon2 = PokemonListUiModel(
        2,
        "Alakazam",
        "img2"
    )
    val pokemon3 = PokemonListUiModel(
        3,
        "Bulba",
        "img3"
    )
    val pokemon4 = PokemonListUiModel(
        4,
        "Chansey",
        "img4"
    )
    val detail= PokemonDetailUiModel(
        1,
        "Charizard",
        "",
        PhysicalInfoUiModel(210f,"m"),
        PhysicalInfoUiModel(120f,"kg"),
        listOf(
            PokemonStatUiModel("HP",300,1f),
            PokemonStatUiModel("ATK",150,0.5f),
            PokemonStatUiModel("DEF",100, 0.33f),
            PokemonStatUiModel("SP ATK",300,1f),
            PokemonStatUiModel("SP DEF",100, 0.33f),
            PokemonStatUiModel("SPEED",150,0.5f)
        ),
        typeList = listOf("fire","flying")
    )
    val pokemonList= listOf(pokemon1, pokemon2, pokemon3, pokemon4)
}