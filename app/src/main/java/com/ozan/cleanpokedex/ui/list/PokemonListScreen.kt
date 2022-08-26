package com.ozan.cleanpokedex.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ozan.cleanpokedex.ui.navigation.NavigationScreen
import com.ozan.cleanpokedex.ui.preview.PokemonPreview
import com.ozan.cleanpokedex.ui.theme.PokedexShapes
import com.ozan.cleanpokedex.ui.theme.PokedexTheme
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonListUiModel
import com.ozan.cleanpokedex.ui.util.CenteredError
import com.ozan.cleanpokedex.ui.util.CircularLoading
import com.ozan.cleanpokedex.ui.util.PokemonImage
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListVm = hiltViewModel(),
) {
    val uiState by viewModel.state.observeAsState()

    uiState?.let { state ->
        when (state) {
            PokemonListState.Loading ->
                CircularLoading()
            is PokemonListState.Error ->
                CenteredError(stringResource(state.e.messageResId))
            is PokemonListState.ListUpdated ->
                PokemonListContent(
                    pokemonList = state.list,
                    onItemClicked = {
                        navController.navigate(
                            NavigationScreen.Detail.createRoute(it.name)
                        )
                    },
                    onScroll = {
                        viewModel.showNextPage(it)
                    }
                )
        }
    }

}

@Composable
fun PokemonListContent(
    pokemonList: List<PokemonListUiModel>,
    onItemClicked: (PokemonListUiModel) -> Unit,
    onScroll: (Int) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .systemBarsPadding()
    ) {
        PokemonList(pokemonList = pokemonList, onItemClicked = onItemClicked, onScroll = onScroll)
    }

}

@Composable
private fun PokemonList(
    modifier: Modifier = Modifier,
    pokemonList: List<PokemonListUiModel>,
    gridSize: Int = 2,
    onItemClicked: (PokemonListUiModel) -> Unit,
    onScroll: (Int) -> Unit
) {
    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(gridSize),
        content = {
            items(pokemonList) { pokemon ->
                PokemonItem(pokemon) {
                    onItemClicked(pokemon)
                }
            }
        },
        contentPadding = PaddingValues(bottom = 64.dp),
        state = listState
    )
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .debounce(300)
            .map {
                (it + 1) * gridSize
            }
            .collect {
                onScroll(it)
            }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PokemonItem(pokemon: PokemonListUiModel, onItemClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .padding(8.dp),
        onClick = onItemClicked,
        shape = PokedexShapes.medium
    ) {
        Column(
            modifier = Modifier.wrapContentWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "#${pokemon.id}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h5
            )
            PokemonImage(
                Modifier.size(72.dp),
                pokemon.imageUrl,
                pokemon.name
            )
            Text(
                text = pokemon.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonListPreview() {
    PokedexTheme(
        darkTheme = true
    ) {
        PokemonListContent(
            pokemonList = PokemonPreview.pokemonList,
            onItemClicked = {},
            onScroll = {}
        )
    }
}