package com.ozan.cleanpokedex.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ozan.cleanpokedex.ui.detail.PokemonDetailScreen
import com.ozan.cleanpokedex.ui.detail.PokemonDetailVm
import com.ozan.cleanpokedex.ui.list.PokemonListScreen
import com.ozan.cleanpokedex.ui.list.PokemonListVm

@ExperimentalAnimationApi
@Composable
fun MainNavigation(
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .semantics { this.testTagsAsResourceId = true },
        navController = navController,
        startDestination = NavigationScreen.PokemonList.route
    ) {
        pokemonList(navController)
        pokemonDetail(navController)
    }
}

private fun NavGraphBuilder.pokemonList(navController: NavHostController) {
    composable(
        route = NavigationScreen.PokemonList.route
    ) {
        val viewModel = hiltViewModel<PokemonListVm>()
        val uiState by viewModel.state.collectAsStateWithLifecycle()
        PokemonListScreen(
            uiState = uiState,
            navigateDetail = {
                navController.navigate(
                    NavigationScreen.PokemonDetail.createRoute(it.name)
                )
            },
            loadPage = {
                viewModel.showNextPage(it)
            }
        )
    }
}

private fun NavGraphBuilder.pokemonDetail(navController: NavHostController) {
    composable(
        route = NavigationScreen.PokemonDetail.route,
        arguments = listOf(navArgument("name") { type = NavType.StringType }),
    ) {
        val viewModel = hiltViewModel<PokemonDetailVm>()
        val uiState by viewModel.state.collectAsStateWithLifecycle()
        PokemonDetailScreen(
            uiState = uiState,
            onBackClicked = {
                navController.popBackStack()
            }
        )
    }
}
