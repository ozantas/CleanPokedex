package com.ozan.cleanpokedex.di.module

import com.ozan.cleanpokedex.data.repository.PokemonRepository
import com.ozan.cleanpokedex.domain.mapper.PokemonMapper
import com.ozan.cleanpokedex.domain.usecase.GetPokemonDetailUseCase
import com.ozan.cleanpokedex.domain.usecase.GetPokemonListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun getPokemonListUseCase(pokemonRepository: PokemonRepository, pokemonMapper: PokemonMapper) =
            GetPokemonListUseCase(pokemonRepository,pokemonMapper)

    @Provides
    fun getPokemonDetailUseCase(pokemonRepository: PokemonRepository, pokemonMapper: PokemonMapper)=
        GetPokemonDetailUseCase(pokemonRepository, pokemonMapper)

}