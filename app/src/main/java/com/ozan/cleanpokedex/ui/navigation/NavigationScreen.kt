package com.ozan.cleanpokedex.ui.navigation


sealed class NavigationScreen(val route: String) {

    data object PokemonList : NavigationScreen("list")
    data object PokemonDetail : NavigationScreen("detail/{name}") {
        fun createRoute(pokemonName: String): String =
            "detail/${pokemonName}"
    }

}