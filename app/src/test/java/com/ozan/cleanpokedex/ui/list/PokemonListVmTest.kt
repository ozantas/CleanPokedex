package com.ozan.cleanpokedex.ui.list

import android.os.Build
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.ozan.cleanpokedex.BaseTest
import com.ozan.cleanpokedex.PokemonTestFake
import com.ozan.cleanpokedex.data.Resource
import com.ozan.cleanpokedex.domain.usecase.GetPokemonListUseCase
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonListUiModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class PokemonListVmTest : BaseTest() {

    @RelaxedMockK
    lateinit var getPokemonListUseCase: GetPokemonListUseCase
    private lateinit var viewModel: PokemonListVm

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = PokemonListVm(getPokemonListUseCase)
    }

    @Test
    fun `given scroll position 70 percent when scrolled then show next page`() =
        coroutineRule.runTest {
            val observer: Observer<PokemonListState> = mockk(relaxUnitFun = true)
            viewModel.state.observeForever(observer)

            coEvery { getPokemonListUseCase.getPokemonList() } returns
                    Resource.Success(listOf())

            viewModel.showNextPage(70)

            val stateSlot = mutableListOf<PokemonListState>()
            verify { observer.onChanged(capture(stateSlot)) }


            Truth.assertThat(stateSlot.size).isEqualTo(2)
            Truth.assertThat(stateSlot.first())
                .isInstanceOf(PokemonListState.Loading::class.java)
            Truth.assertThat(stateSlot.last())
                .isInstanceOf(PokemonListState.ListUpdated::class.java)
        }

    @Test
    fun `given invalid position percent when scrolled then do nothing`() =
        coroutineRule.runTest {
            val observer: Observer<PokemonListState> = mockk(relaxUnitFun = true)
            viewModel.state.observeForever(observer)

            coEvery { getPokemonListUseCase.getPokemonList() } returns
                    Resource.Success(listOf())

            viewModel.showNextPage(-10)

            val stateSlot = mutableListOf<PokemonListState>()
            verify { observer.onChanged(capture(stateSlot)) }

            Truth.assertThat(stateSlot.size).isEqualTo(1)
            Truth.assertThat(stateSlot.first()).isInstanceOf(PokemonListState.Loading::class.java)
        }

}