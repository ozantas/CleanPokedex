package com.ozan.cleanpokedex.ui.detail

import androidx.lifecycle.*
import com.ozan.cleanpokedex.domain.usecase.GetPokemonDetailUseCase
import com.ozan.cleanpokedex.extension.onErrorResource
import com.ozan.cleanpokedex.extension.onSuccessResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailVm @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    init {
        savedStateHandle.get<String>("name")?.let { pokemonName ->
            showDetail(pokemonName)
        }
    }

    private val _state= MutableLiveData<PokemonDetailState>(PokemonDetailState.Loading)
    val state: LiveData<PokemonDetailState> = _state

    private fun showDetail(pokemonName: String) =
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonDetailUseCase.getDetail(pokemonName)
                .onSuccessResource {
                    val state= PokemonDetailState.Success(it)
                    _state.postValue(state)
                }
                .onErrorResource {
                    val state= PokemonDetailState.Error(PokemonDetailError.CannotLoad())
                    _state.postValue(state)
                }
        }

}