package com.ozan.cleanpokedex.domain.mapper

import com.ozan.cleanpokedex.data.datasource.database.entity.PokemonListEntity
import com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail.PokemonDetailResponse
import com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail.StatListItem
import com.ozan.cleanpokedex.data.datasource.network.model.pokemonlist.PokemonListItemModel
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonDetailUiModel
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonListUiModel
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonStatUiModel
import javax.inject.Inject

class PokemonMapper @Inject constructor(
    private val imageBaseUrl: String
) {

    fun toUiModelList(modelList: List<PokemonListEntity>): List<PokemonListUiModel> {
        val uiModelList= mutableListOf<PokemonListUiModel>()
        modelList.forEach {
            val pokemon= PokemonListUiModel(
                    id= it.id,
                    name = it.name,
                    "$imageBaseUrl${it.id}.png"
            )
            uiModelList.add(pokemon)
        }
        return uiModelList
    }

    fun toDetailUiModel(response: PokemonDetailResponse): PokemonDetailUiModel {
        return PokemonDetailUiModel(
            name = response.name,
            imageUrl= "$imageBaseUrl${response.id}.png",
            type= response.types.first().type.type,
            height = response.height.toString(),
            weight = response.weight.toString(),
            statList = toStatUiModel(response.stats)
        )
    }

    private fun toStatUiModel(statModelList: List<StatListItem>): List<PokemonStatUiModel> {
        return statModelList.map {
            PokemonStatUiModel(
                name = it.stat.name.statName,
                stat = it.baseStat
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

}