package com.ozan.cleanpokedex.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozan.cleanpokedex.domain.usecase.GetPokemonDetailUseCase
import com.ozan.cleanpokedex.extension.onSuccessResource
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailVm @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    private val _progressVisibility= MutableLiveData<Boolean>()
    val progressVisibility: LiveData<Boolean> = _progressVisibility

    private val _pokemonDetail= MutableLiveData<PokemonDetailUiModel>()
    val pokemonDetail: MutableLiveData<PokemonDetailUiModel> = _pokemonDetail

    fun showDetail(pokemonName: String?) =
        viewModelScope.launch(Dispatchers.IO) {
            if (pokemonName.isNullOrEmpty()) return@launch
            _progressVisibility.postValue(true)
            getPokemonDetailUseCase.getDetail(pokemonName)
                .onSuccessResource {
                    _pokemonDetail.postValue(it)
                }
                .also {
                    _progressVisibility.postValue(false)
                }
        }

}