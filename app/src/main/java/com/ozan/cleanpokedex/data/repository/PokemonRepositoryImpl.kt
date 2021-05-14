package com.ozan.cleanpokedex.data.repository

import com.ozan.cleanpokedex.data.Resource
import com.ozan.cleanpokedex.data.datasource.database.dao.PokemonDao
import com.ozan.cleanpokedex.data.datasource.database.entity.PokemonListEntity
import com.ozan.cleanpokedex.data.datasource.memory.PokemonDetailDataSource
import com.ozan.cleanpokedex.data.datasource.memory.PokemonListPageDataSource
import com.ozan.cleanpokedex.data.datasource.network.PokedexApi
import com.ozan.cleanpokedex.data.datasource.network.model.pokemondetail.PokemonDetailResponse
import com.ozan.cleanpokedex.domain.mapper.PokemonMapper
import com.ozan.cleanpokedex.extension.map
import com.ozan.cleanpokedex.extension.onSuccessResource
import com.ozan.cleanpokedex.extension.onSuccessResourceSuspend
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokedexApi: PokedexApi,
    private val listPageDataSource: PokemonListPageDataSource,
    private val pokemonDetailDataSource: PokemonDetailDataSource,
    private val pokemonDao: PokemonDao,
    private val pokemonMapper: PokemonMapper
) : PokemonRepository, Repository() {

    override suspend fun getPokemonList(): Resource<List<PokemonListEntity>> {
        val offset = listPageDataSource.getOffset()
        val pageSize = listPageDataSource.pageSize
        val listFromDb = pokemonDao.getPokemonList(pageSize, offset)
        return if (listFromDb.isEmpty()) {
            sendPokemonListRequest(pageSize, offset)
        } else {
            Resource.Success(listFromDb)
        }.also {
            listPageDataSource.increasePage()
        }
    }

    private suspend fun sendPokemonListRequest(
        pageSize: Int,
        offset: Int
    ): Resource<List<PokemonListEntity>> {
        return networkCall { pokedexApi.getPokemonList(pageSize, offset) }
            .map(
                onSuccess = {
                    val entityList = pokemonMapper.toEntityList(it.pokemonListItems)
                    Resource.Success(entityList)
                },
                onError = {
                    Resource.Error(it)
                }
            ).onSuccessResourceSuspend {
                pokemonDao.insert(it)
            }
    }

    override suspend fun getPokemonDetail(name: String): Resource<PokemonDetailResponse> {
        return pokemonDetailDataSource.getDetail(name)
            ?.let {
                Resource.Success(it)
            }
            ?: sendDetailRequest(name)
    }

    private suspend fun sendDetailRequest(name: String) = networkCall {
        pokedexApi.getPokemonDetail(name)
    }.onSuccessResource {
        pokemonDetailDataSource.save(name, it)
    }

}