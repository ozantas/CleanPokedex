package com.ozan.cleanpokedex.ui.detail.recycler.stats

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozan.cleanpokedex.R
import com.ozan.cleanpokedex.databinding.ItemPokemonStatBinding
import com.ozan.cleanpokedex.extension.inflateBinding
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonStatUiModel

class PokemonStatsRecyclerAdapter : RecyclerView.Adapter<PokemonStatsViewHolder>() {

    private var statList = listOf<PokemonStatUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonStatsViewHolder {
        val binding= parent.inflateBinding<ItemPokemonStatBinding>(R.layout.item_pokemon_stat)
        return PokemonStatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonStatsViewHolder, position: Int) {
        holder.bind(statList[position])
    }

    override fun getItemCount()= statList.size

    fun show(newList: List<PokemonStatUiModel>) {
        statList= newList
        notifyDataSetChanged()
    }

}