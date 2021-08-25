package com.ozan.cleanpokedex.ui.list

import androidx.lifecycle.Observer
import com.ozan.cleanpokedex.BaseTest
import com.ozan.cleanpokedex.domain.usecase.GetPokemonListUseCase
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonListUiModel
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
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
        val observer = spyk<Observer<List<PokemonListUiModel>>>(Observer {})
        val position = 70
        val itemCount= 100
        viewModel.showNextPage(position)
        coVerify { getPokemonListUseCase.getPokemonList() }
    }

}