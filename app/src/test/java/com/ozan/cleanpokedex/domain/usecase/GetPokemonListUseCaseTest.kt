package com.ozan.cleanpokedex.domain.usecase

import com.google.common.truth.Truth
import com.ozan.cleanpokedex.BaseTest
import com.ozan.cleanpokedex.PokemonTestFake
import com.ozan.cleanpokedex.data.Resource
import com.ozan.cleanpokedex.data.repository.PokemonRepository
import com.ozan.cleanpokedex.domain.mapper.PokemonMapper
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class GetPokemonListUseCaseTest : BaseTest() {

    private lateinit var getPokemonListUseCase: GetPokemonListUseCase

    private val mapper= PokemonMapper("")
    @MockK
    private lateinit var pokemonRepository: PokemonRepository

    @Before
    override fun setUp() {
        super.setUp()
        getPokemonListUseCase= GetPokemonListUseCase(pokemonRepository, mapper)
    }

    @Test
    fun `given success response when get pokemon list then increase page number`()= coroutineRule.runTest {

        coEvery {
            pokemonRepository.getPokemonList(any(),any())
        } returns Resource.Success(
            PokemonTestFake.createEntityList(50)
        )
        getPokemonListUseCase.getPokemonList()

        Truth.assertThat(getPokemonListUseCase.getPage()).isEqualTo(1)
    }

    @Test
    fun `given error response when get pokemon list then keep page number`()= coroutineRule.runTest {

        coEvery {
            pokemonRepository.getPokemonList(any(),any())
        } returns Resource.Error(Exception())
        getPokemonListUseCase.getPokemonList()

        Truth.assertThat(getPokemonListUseCase.getPage()).isEqualTo(0)
    }

}