package com.ozan.cleanpokedex.di.module

import com.ozan.cleanpokedex.data.repository.PokemonRepository
import com.ozan.cleanpokedex.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun pokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository

}