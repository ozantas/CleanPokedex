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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class PokemonListVm @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PokemonListState>(PokemonListState.Loading)
    val state: StateFlow<PokemonListState> = _state

    private var pokemonList = listOf<PokemonListUiModel>()

    init {
        showList()
    }

    private fun showList() =
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonListUseCase.getPokemonList()
                .onSuccessResource { pokemonUiModelList ->
                    _state.update {
                        pokemonList = pokemonList.mergeWith(pokemonUiModelList)
                        PokemonListState.ListUpdated(pokemonList)
                    }
                }
                .onErrorResource {
                    _state.update {
                        val error = PokemonListError.CannotLoad()
                        PokemonListState.Error(error)
                    }
                }
        }

    fun showNextPage(itemPosition: Int) {
        if (itemPosition >= pokemonList.size * 0.5) {
            showList()
        }
    }

}