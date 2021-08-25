package com.ozan.cleanpokedex.domain.usecase

import com.ozan.cleanpokedex.data.Resource
import com.ozan.cleanpokedex.data.repository.PokemonRepository
import com.ozan.cleanpokedex.domain.mapper.PokemonMapper
import com.ozan.cleanpokedex.extension.map
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonDetailUiModel
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val pokemonMapper: PokemonMapper
) {

    suspend fun getDetail(pokemonName: String): Resource<PokemonDetailUiModel> {
        return pokemonRepository.getPokemonDetail(pokemonName.lowercase())
            .map(
                onSuccess = {
                    val uiModel= pokemonMapper.toDetailUiModel(it)
                    Resource.Success(uiModel)
                },
                onError = {
                    Resource.Error(it)
                }
            )
    }


}