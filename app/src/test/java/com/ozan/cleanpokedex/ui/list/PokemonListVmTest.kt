package com.ozan.cleanpokedex.ui.list

import com.ozan.cleanpokedex.BaseTest
import com.ozan.cleanpokedex.domain.usecase.GetPokemonListUseCase
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class PokemonListVmTest : BaseTest() {

    @MockK
    lateinit var getPokemonListUseCase: GetPokemonListUseCase
    private lateinit var viewModel: PokemonListVm

    @Before
    override fun setUp() {
        super.setUp()
        viewModel= PokemonListVm(getPokemonListUseCase)
    }

    @Test
    fun `given scroll position at 70 percent when scrolled then show next page`()= coroutineRule.runTest {
        val position = 70
        val itemCount= 100
        viewModel.showNextPage(position,itemCount)
        coVerify { getPokemonListUseCase.getPokemonList() }
    }

}