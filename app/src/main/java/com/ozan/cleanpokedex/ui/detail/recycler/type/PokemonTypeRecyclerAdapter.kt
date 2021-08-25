package com.ozan.cleanpokedex.ui.detail.recycler.type

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozan.cleanpokedex.R
import com.ozan.cleanpokedex.databinding.ItemPokemonTypeBinding
import com.ozan.cleanpokedex.extension.inflateBinding

class PokemonTypeRecyclerAdapter : RecyclerView.Adapter<PokemonTypeViewHolder>() {

    private var typeList = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypeViewHolder {
        val binding = parent.inflateBinding<ItemPokemonTypeBinding>(R.layout.item_pokemon_type)
        return PokemonTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonTypeViewHolder, position: Int) {
        holder.bind(typeList[position])
    }

    override fun getItemCount() = typeList.size

    fun show(newList: List<String>) {
        typeList= newList
        notifyDataSetChanged()
    }

}