package com.ozan.cleanpokedex.ui.list.recycler

import androidx.recyclerview.widget.RecyclerView
import com.ozan.cleanpokedex.databinding.ItemPokemonBinding
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonListUiModel

class PokemonListViewHolder constructor(
    private val binding: ItemPokemonBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: PokemonListUiModel, onClick: (PokemonListUiModel) -> Unit) {
        binding.model= model
        binding.root.setOnClickListener {
            onClick(model)
        }
    }

}