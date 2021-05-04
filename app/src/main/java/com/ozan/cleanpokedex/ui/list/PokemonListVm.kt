package com.ozan.cleanpokedex.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozan.cleanpokedex.domain.usecase.GetPokemonListUseCase
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

    private val _pokemonList = MutableLiveData<List<PokemonListUiModel>>()
    val pokemonList: LiveData<List<PokemonListUiModel>> = _pokemonList

    fun showList() =
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonListUseCase.getPokemonList()
                .onSuccessResource {
                    val list = _pokemonList.value?.toMutableList()
                        ?: mutableListOf()
                    list.addAll(it)
                    _pokemonList.postValue(list)
                }
                .onErrorResource {

                }
        }

    fun showNextPage(itemPosition: Int, totalItemCount: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            if (itemPosition >= totalItemCount * 0.7) {
                showList()
            }
        }

}