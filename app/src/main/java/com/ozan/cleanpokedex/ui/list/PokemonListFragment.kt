package com.ozan.cleanpokedex.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ozan.cleanpokedex.R
import com.ozan.cleanpokedex.databinding.FragPokemonListBinding
import com.ozan.cleanpokedex.extension.add
import com.ozan.cleanpokedex.extension.observeNotNull
import com.ozan.cleanpokedex.ui.base.BaseFragment
import com.ozan.cleanpokedex.ui.detail.PokemonDetailFragment
import com.ozan.cleanpokedex.ui.list.recycler.PokemonListRecyclerAdapter
import com.ozan.cleanpokedex.ui.util.GridItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : BaseFragment<FragPokemonListBinding>() {

    companion object {
        fun new() =
            PokemonListFragment().apply {
                arguments = Bundle()
            }
    }

    override val getLayoutId = R.layout.frag_pokemon_list
    override val viewModel by viewModels<PokemonListVm>()

    private val itemDecoration by lazy {
        val spanCount = (binding?.rvPokemon?.layoutManager as GridLayoutManager).spanCount
        GridItemDecoration(spanCount, 32)
    }

    private val pokemonListAdapter by lazy {
        PokemonListRecyclerAdapter {
            parentFragmentManager.add(
                R.id.frmMain,
                PokemonDetailFragment.new(it.name),
                true
            )
        }.apply { setHasStableIds(true) }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                val adapter = binding?.rvPokemon?.layoutManager as GridLayoutManager
                val lastVisibleItemPosition = adapter.findLastCompletelyVisibleItemPosition()
                val totalItemCount = adapter.itemCount
                viewModel.showNextPage(lastVisibleItemPosition, totalItemCount)
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.showList()
        subscribePokemonList()
    }

    private fun initRecyclerView() {
        binding?.rvPokemon?.apply {
            addItemDecoration(itemDecoration)
            adapter = pokemonListAdapter
            addOnScrollListener(scrollListener)
        }
    }

    private fun subscribePokemonList() {
        viewModel.pokemonList.observeNotNull(viewLifecycleOwner) {
            pokemonListAdapter.show(it)
        }
    }

}