package com.ozan.cleanpokedex.di.module

import com.ozan.cleanpokedex.data.datasource.memory.PokemonDetailDataSource
import com.ozan.cleanpokedex.data.datasource.memory.PokemonListPageDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun pokemonListPageDataSource()= PokemonListPageDataSource()

    @Singleton
    @Provides
    fun pokemonDetailDataSource()= PokemonDetailDataSource()

}