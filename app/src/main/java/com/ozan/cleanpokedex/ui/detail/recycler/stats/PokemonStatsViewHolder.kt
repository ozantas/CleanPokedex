package com.ozan.cleanpokedex.ui.detail.recycler.stats

import androidx.recyclerview.widget.RecyclerView
import com.ozan.cleanpokedex.databinding.ItemPokemonStatBinding
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonStatUiModel

class PokemonStatsViewHolder constructor(
    private val binding: ItemPokemonStatBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: PokemonStatUiModel) {
        binding.model= model
    }

}