package com.ozan.cleanpokedex.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.ozan.cleanpokedex.ui.detail.PokemonDetailScreen
import com.ozan.cleanpokedex.ui.detail.PokemonDetailVm
import com.ozan.cleanpokedex.ui.list.PokemonListScreen
import com.ozan.cleanpokedex.ui.list.PokemonListVm

@ExperimentalAnimationApi
@Composable
fun MainNavigation(
    navController: NavHostController
) {
    AnimatedNavHost(
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