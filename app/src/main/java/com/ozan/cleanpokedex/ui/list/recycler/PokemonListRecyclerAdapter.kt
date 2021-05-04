package com.ozan.cleanpokedex.ui.list.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ozan.cleanpokedex.R
import com.ozan.cleanpokedex.databinding.ItemPokemonBinding
import com.ozan.cleanpokedex.extension.inflateBinding
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PokemonListUiModel

class PokemonListRecyclerAdapter constructor(
    private val onClick: (PokemonListUiModel) -> Unit
): RecyclerView.Adapter<PokemonListViewHolder>() {

    private var pokemonList = listOf<PokemonListUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val binding = parent.inflateBinding<ItemPokemonBinding>(R.layout.item_pokemon)
        return PokemonListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.bind(pokemonList[position], onClick)
    }

    override fun getItemCount() = pokemonList.size

    override fun getItemId(position: Int)= pokemonList[position].id.toLong()

    fun show(newList: List<PokemonListUiModel>) {
        val diffCallback = PokemonListDiffUtil(pokemonList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        pokemonList = newList
    }

}