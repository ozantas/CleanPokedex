package com.ozan.cleanpokedex.ui.detail

import androidx.lifecycle.*
import com.ozan.cleanpokedex.domain.usecase.GetPokemonDetailUseCase
import com.ozan.cleanpokedex.extension.onErrorResource
import com.ozan.cleanpokedex.extension.onSuccessResource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

    private val _state= MutableStateFlow<PokemonDetailState>(PokemonDetailState.Loading)
    val state: StateFlow<PokemonDetailState> = _state

    private fun showDetail(pokemonName: String) =
        viewModelScope.launch(Dispatchers.Default) {
            getPokemonDetailUseCase.getDetail(pokemonName)
                .onSuccessResource { uiModel ->
                    _state.update {
                        PokemonDetailState.Success(uiModel)
                    }
                }
                .onErrorResource {
                    _state.update {
                        PokemonDetailState.Error(PokemonDetailError.CannotLoad())
                    }
                }
        }

}