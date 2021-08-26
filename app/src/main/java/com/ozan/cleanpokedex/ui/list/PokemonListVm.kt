package com.ozan.cleanpokedex.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozan.cleanpokedex.domain.usecase.GetPokemonListUseCase
import com.ozan.cleanpokedex.extension.mergeWith
import com.ozan.cleanpokedex.extension.onErrorResource
import com.ozan.cleanpokedex.extension.onSuccessResource
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonListUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListVm @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _state = MutableLiveData<PokemonListState>(PokemonListState.Loading)
    val state: LiveData<PokemonListState> = _state

    private var pokemonList = listOf<PokemonListUiModel>()

    init {
        showList()
    }

    private fun showList() =
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonListUseCase.getPokemonList()
                .onSuccessResource {
                    pokemonList = pokemonList.mergeWith(it)
                    _state.postValue(PokemonListState.ListUpdated(pokemonList))
                }
                .onErrorResource {
                    val error = PokemonListError.CannotLoad()
                    _state.postValue(PokemonListState.Error(error))
                }
        }

    fun showNextPage(itemPosition: Int) {
        if (itemPosition >= pokemonList.size * 0.5) {
            showList()
        }
    }

}