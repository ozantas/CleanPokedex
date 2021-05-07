package com.ozan.cleanpokedex.ui.detail.recycler.type

import androidx.recyclerview.widget.RecyclerView
import com.ozan.cleanpokedex.databinding.ItemPokemonTypeBinding

class PokemonTypeViewHolder constructor(
    private val binding: ItemPokemonTypeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(type: String) {
        binding.model= type
    }

}