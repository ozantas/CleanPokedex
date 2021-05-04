package com.ozan.cleanpokedex.ui.list.recycler

import androidx.recyclerview.widget.DiffUtil
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonListUiModel

class PokemonListDiffUtil constructor(
    private val oldList: List<PokemonListUiModel>,
    private val newList: List<PokemonListUiModel>
): DiffUtil.Callback() {

    override fun getOldListSize()= oldList.size

    override fun getNewListSize()= newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}