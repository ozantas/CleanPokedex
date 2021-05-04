package com.ozan.cleanpokedex.di.module

import com.ozan.cleanpokedex.di.qualifier.ImageBaseUrl
import com.ozan.cleanpokedex.domain.mapper.PokemonMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MapperModule {

    @Provides
    fun pokemonMapper(@ImageBaseUrl imageBaseUrl: String): PokemonMapper {
        return PokemonMapper(imageBaseUrl)
    }

}