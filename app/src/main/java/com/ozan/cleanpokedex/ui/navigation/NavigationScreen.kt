package com.ozan.cleanpokedex.ui.navigation


sealed class NavigationScreen(val route: String) {

    object List : NavigationScreen("list")
    object Detail : NavigationScreen("detail/{name}") {
        fun createRoute(pokemonName: String): String =
            "detail/${pokemonName}"
    }

}