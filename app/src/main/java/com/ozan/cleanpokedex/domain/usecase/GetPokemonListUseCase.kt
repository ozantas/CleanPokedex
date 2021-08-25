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

    private val pageSize = 100
    private var currentPage = 0

    suspend fun getPokemonList(): Resource<List<PokemonListUiModel>> {
        return pokemonRepository.getPokemonList(pageSize, pageSize * currentPage)
            .map(
                onSuccess = {
                    currentPage++
                    val uiModelList = pokemonMapper.toUiModelList(it)
                    Resource.Success(uiModelList)
                },
                onError = {
                    Resource.Error(it)
                }
            )
    }

    fun getPage()= currentPage

}