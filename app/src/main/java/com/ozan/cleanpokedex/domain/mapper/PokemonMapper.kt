package com.ozan.cleanpokedex.domain.mapper

import com.ozan.cleanpokedex.data.datasource.database.entity.PokemonListEntity
import com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail.PokemonDetailResponse
import com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail.StatListItem
import com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail.TypeModel
import com.ozan.cleanpokedex.data.datasource.network.model.pokemonlist.PokemonListItemModel
import com.ozan.cleanpokedex.extension.capitalizeFirstChar
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PhysicalInfoUiModel
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonDetailUiModel
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonListUiModel
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonStatUiModel
import com.ozan.cleanpokedex.ui.util.PokemonType
import javax.inject.Inject

class PokemonMapper @Inject constructor(
    private val imageBaseUrl: String
) {

    fun toUiModelList(modelList: List<PokemonListEntity>): List<PokemonListUiModel> {
        val uiModelList = mutableListOf<PokemonListUiModel>()
        modelList.forEach {
            val pokemon = PokemonListUiModel(
                id = it.id,
                name = it.name.capitalizeFirstChar(),
                imageUrl = imageBaseUrl.toPokemonImageUrl(it.id)
            )
            uiModelList.add(pokemon)
        }
        return uiModelList
    }

    fun toDetailUiModel(response: PokemonDetailResponse): PokemonDetailUiModel {
        return PokemonDetailUiModel(
            id = response.id,
            name = response.name.capitalizeFirstChar(),
            imageUrl = imageBaseUrl.toPokemonImageUrl(response.id),
            typeList = response.types.map {
                it.type.mapType()
            },
            height = PhysicalInfoUiModel(
                //api sends it in decimetres
                response.height.div(10f),
                "m"
            ),
            weight = PhysicalInfoUiModel(
                //api sends it in hectograms
                response.weight.div(10f),
                "kg"
            ),
            statList = toStatUiModel(response.stats)
        )
    }

    private fun toStatUiModel(statModelList: List<StatListItem>): List<PokemonStatUiModel> {
        return statModelList.map {
            PokemonStatUiModel(
                name = it.stat.name.statName,
                stat = it.baseStat,
                progress = it.baseStat.div(300f)
            )
        }
    }

    fun toEntityList(modelList: List<PokemonListItemModel>): List<PokemonListEntity> {
        return modelList.map {
            PokemonListEntity(
                it.getId(),
                it.name
            )
        }
    }

    private fun PokemonListItemModel.getId(): Int {
        return url.split("/".toRegex()).dropLast(1).last().toInt()
    }

    private fun String.toPokemonImageUrl(pokemonId: Int) =
        "$this${String.format("%03d", pokemonId)}.png"

    private fun TypeModel.mapType(): PokemonType {
        return when(type.lowercase()) {
            "normal" -> PokemonType.Normal
            "fire" -> PokemonType.Fire
            "water" -> PokemonType.Water
            "electric" -> PokemonType.Electric
            "grass" -> PokemonType.Grass
            "ice" -> PokemonType.Ice
            "fighting" -> PokemonType.Fighting
            "poison" -> PokemonType.Poison
            "ground" -> PokemonType.Ground
            "flying" -> PokemonType.Flying
            "psychic" -> PokemonType.Psychic
            "bug" -> PokemonType.Bug
            "rock" -> PokemonType.Rock
            "ghost" -> PokemonType.Ghost
            "dragon" -> PokemonType.Dragon
            "dark" -> PokemonType.Dark
            "steel" -> PokemonType.Steel
            "fairy" -> PokemonType.Fairy
            else -> PokemonType.Normal
        }
    }

}