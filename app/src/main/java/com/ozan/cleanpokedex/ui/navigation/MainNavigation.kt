package com.ozan.cleanpokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.ozan.cleanpokedex.ui.detail.PokemonDetailScreen
import com.ozan.cleanpokedex.ui.detail.PokemonDetailVm
import com.ozan.cleanpokedex.ui.list.PokemonListScreen
import com.ozan.cleanpokedex.ui.list.PokemonListVm

@Composable
fun MainNavigation(
    navController: NavHostController
) {
    NavHost(
        navController,
        startDestination = NavigationScreen.List.route
    ) {
        composable(NavigationScreen.List.route) {
            val viewModel = hiltViewModel<PokemonListVm>()
            PokemonListScreen(navController,viewModel)
        }
        composable(
            NavigationScreen.Detail.route,
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) {
            val viewModel = hiltViewModel<PokemonDetailVm>()
            PokemonDetailScreen(viewModel)
        }
    }
}