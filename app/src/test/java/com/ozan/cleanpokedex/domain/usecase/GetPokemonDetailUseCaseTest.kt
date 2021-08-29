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

class GetPokemonDetailUseCaseTest : BaseTest() {

    lateinit var useCase: GetPokemonDetailUseCase
    @MockK
    lateinit var repository: PokemonRepository

    @Before
    override fun setUp() {
        super.setUp()
        useCase= GetPokemonDetailUseCase(repository, PokemonMapper(""))
    }

    @Test
    fun `given success response when get detail return detail ui model`()= coroutineRule.runTest {
        val name= "Charizard"

        coEvery {
            repository.getPokemonDetail("charizard")
        } returns Resource.Success(PokemonTestFake.detailResponse)

        val result= useCase.getDetail(name)
        Truth.assertThat(result).isInstanceOf(Resource.Success::class.java)
        Truth.assertThat((result as Resource.Success).data.name).isEqualTo(name)
    }

    @Test
    fun `given error response when get detail return error`()= coroutineRule.runTest {
        coEvery {
            repository.getPokemonDetail(any())
        } returns Resource.Error(NullPointerException())

        val result= useCase.getDetail("")
        Truth.assertThat(result).isInstanceOf(Resource.Error::class.java)
    }
}