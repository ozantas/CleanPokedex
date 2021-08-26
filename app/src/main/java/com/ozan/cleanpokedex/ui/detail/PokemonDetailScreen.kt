package com.ozan.cleanpokedex.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ozan.cleanpokedex.ui.preview.PokemonPreview
import com.ozan.cleanpokedex.ui.theme.PokedexTheme
import com.ozan.cleanpokedex.ui.theme.onBackgroundVariant
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PhysicalInfoUiModel
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonDetailUiModel
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonStatUiModel
import com.ozan.cleanpokedex.ui.util.CircularLoading
import com.ozan.cleanpokedex.ui.util.PokemonImage
import com.ozan.cleanpokedex.ui.util.PokemonType

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailVm = hiltViewModel()
) {
    val uiState by viewModel.state.observeAsState()
    uiState?.let {
        when (it) {
            is PokemonDetailState.Loading ->
                CircularLoading()
            is PokemonDetailState.Success ->
                PokemonDetailContent(it.detail)
        }
    }
}

@Composable
fun PokemonDetailContent(detail: PokemonDetailUiModel) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        detail.typeList.first().color,
                        MaterialTheme.colors.background,
                        MaterialTheme.colors.background,
                    )
                )
            ),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokemonImage(
            Modifier.size(132.dp),
            detail.imageUrl,
            detail.name
        )
        PokemonInfo(detail.id, detail.name, detail.typeList)
        PhysicalInfoRow(listOf(detail.height, detail.weight))
        Spacer(modifier = Modifier.padding(8.dp))
        StatTable(detail.statList)
        Spacer(modifier = Modifier.padding(8.dp))
    }
}

@Composable
private fun PokemonInfo(id: Int, name: String, typeList: List<PokemonType>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "#${id}",
            style = MaterialTheme.typography.h4
        )
        Text(
            text = name,
            style = MaterialTheme.typography.h1
        )
        TypeList(typeList)
    }
}

@Composable
fun TypeList(typeList: List<PokemonType>) {
    LazyRow(
        content = {
            items(typeList) { type ->
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = type.name,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackgroundVariant
                )
            }
        }
    )
}

@Composable
fun PhysicalInfoRow(infoList: List<PhysicalInfoUiModel>) {
    LazyRow(
        content = {
            items(infoList) { info ->
                PhysicalInfoItem(
                    modifier = Modifier.padding(8.dp),
                    info = info
                )
            }
        }
    )
}

@Composable
private fun PhysicalInfoItem(modifier: Modifier, info: PhysicalInfoUiModel) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = info.value.toString(),
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = info.unit,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun StatTable(statList: List<PokemonStatUiModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        content = {
            items(statList) { stat ->
                Stat(
                    modifier = Modifier.padding(8.dp),
                    stat = stat
                )
            }
        }
    )
}

@Composable
fun Stat(modifier: Modifier, stat: PokemonStatUiModel) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stat.name)
        LinearProgressIndicator(
            progress = stat.progress,
            color = MaterialTheme.colors.secondary,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailContentPreview() {
    PokedexTheme(
        darkTheme = true
    ) {
        PokemonDetailContent(
            detail = PokemonPreview.detail
        )
    }
}