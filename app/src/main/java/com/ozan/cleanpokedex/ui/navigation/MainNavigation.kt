package com.ozan.cleanpokedex.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.ozan.cleanpokedex.ui.detail.PokemonDetailScreen
import com.ozan.cleanpokedex.ui.detail.PokemonDetailVm
import com.ozan.cleanpokedex.ui.list.PokemonListScreen
import com.ozan.cleanpokedex.ui.list.PokemonListVm
import kotlin.math.max

@ExperimentalAnimationApi
@Composable
fun MainNavigation(
    navController: NavHostController,
    maxWidth: Int
) {
    AnimatedNavHost(
        navController,
        startDestination = NavigationScreen.List.route
    ) {
        composable(
            route = NavigationScreen.List.route
        ) {
            val viewModel = hiltViewModel<PokemonListVm>()
            PokemonListScreen(navController, viewModel)
        }
        composable(
            route = NavigationScreen.Detail.route,
            arguments = listOf(navArgument("name") { type = NavType.StringType }),
            enterTransition = enterTransition(maxWidth),
            popExitTransition = exitTransition(maxWidth/2)
        ) {
            val viewModel = hiltViewModel<PokemonDetailVm>()
            PokemonDetailScreen(viewModel)
        }
    }
}

@ExperimentalAnimationApi
private fun exitTransition(offset: Int= 300): AnimatedContentScope<String>.(initial: NavBackStackEntry, target: NavBackStackEntry) -> ExitTransition? =
    { _, target ->
        slideOutHorizontally(
            targetOffsetX = { offset },
            animationSpec = tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            )
        ) + fadeOut(animationSpec = tween(300))
    }

@ExperimentalAnimationApi
private fun enterTransition(offset: Int= 300): AnimatedContentScope<String>.(initial: NavBackStackEntry, target: NavBackStackEntry) -> EnterTransition? =
    { _, _ ->
        slideInHorizontally(
            initialOffsetX = { offset },
            animationSpec = tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            )
        ) + fadeIn(animationSpec = tween(300))
    }