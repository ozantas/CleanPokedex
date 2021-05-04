package com.ozan.cleanpokedex.domain.usecase

import com.ozan.cleanpokedex.data.Resource
import com.ozan.cleanpokedex.data.repository.PokemonRepository
import com.ozan.cleanpokedex.domain.mapper.PokemonMapper
import com.ozan.cleanpokedex.extension.map
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonListUiModel
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(
        private val pokemonRepository: PokemonRepository,
        private val pokemonMapper: PokemonMapper
) {

    suspend fun getPokemonList(): Resource<List<PokemonListUiModel>> {
        return pokemonRepository.getPokemonList()
                .map(
                        onSuccess = { modelList ->
                            val uiModelList= pokemonMapper.toUiModelList(modelList)
                            Resource.Success(uiModelList)
                        },
                        onError = {
                            Resource.Error(it)
                        }
                )
    }

}