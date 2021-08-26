package com.ozan.cleanpokedex.di.module

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ozan.cleanpokedex.data.datasource.network.PokedexApi
import com.ozan.cleanpokedex.di.qualifier.ApiBaseUrl
import com.ozan.cleanpokedex.di.qualifier.ImageBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun loggingInterceptor() =
    HttpLoggingInterceptor().apply {
      level= HttpLoggingInterceptor.Level.BODY
    }

  @Provides
  @Singleton
  fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .build()
  }

  @Singleton
  @Provides
  fun jsonConverter(): Converter.Factory {
    return Json {
      ignoreUnknownKeys= true
    }.asConverterFactory("application/json".toMediaType())
  }

  @Provides
  @Singleton
  fun retrofit(okHttpClient: OkHttpClient, @ApiBaseUrl baseUrl: String, converter: Converter.Factory): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(baseUrl)
      .addConverterFactory(converter)
      .build()
  }

  @Provides
  @Singleton
  fun pokedexService(retrofit: Retrofit): PokedexApi {
    return retrofit.create(PokedexApi::class.java)
  }

  @ApiBaseUrl
  @Singleton
  @Provides
  fun apiBaseUrl()= "https://pokeapi.co/api/v2/"

  @ImageBaseUrl
  @Singleton
  @Provides
  fun imageBaseUrl()= "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/"

}
