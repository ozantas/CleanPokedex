package com.ozan.cleanpokedex.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ozan.cleanpokedex.R
import com.ozan.cleanpokedex.databinding.FragPokemonDetailBinding
import com.ozan.cleanpokedex.extension.observeNotNull
import com.ozan.cleanpokedex.ui.base.BaseFragment
import com.ozan.cleanpokedex.ui.bindingadapter.bindImageUrl
import com.ozan.cleanpokedex.ui.detail.recycler.PokemonStatsRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : BaseFragment<FragPokemonDetailBinding>() {

    companion object {
        private const val KEY_POKEMON_NAME= "KEY_POKEMON_NAME"
        fun new(pokemonName: String) =
            PokemonDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_POKEMON_NAME, pokemonName)
                }
            }
    }

    override val viewModel by viewModels<PokemonDetailVm>()
    override val getLayoutId= R.layout.frag_pokemon_detail

    private val pokemonName by lazy {
        arguments?.getString(KEY_POKEMON_NAME)
    }

    private val statRecyclerAdapter by lazy {
        PokemonStatsRecyclerAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.rvStats?.adapter= statRecyclerAdapter
        viewModel.showDetail(pokemonName)
        subscribePokemonDetail()
    }

    private fun subscribePokemonDetail() {
        viewModel.pokemonDetail.observeNotNull(viewLifecycleOwner) {
            binding?.ivPokemon?.bindImageUrl(it.imageUrl)
            binding?.tvName?.text= it.name
            binding?.tvType?.text= it.type
            statRecyclerAdapter.show(it.statList)
        }
    }

}