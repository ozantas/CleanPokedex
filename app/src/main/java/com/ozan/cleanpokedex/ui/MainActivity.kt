package com.ozan.cleanpokedex.ui

import android.os.Bundle
import com.ozan.cleanpokedex.R
import com.ozan.cleanpokedex.databinding.ActMainBinding
import com.ozan.cleanpokedex.extension.replace
import com.ozan.cleanpokedex.ui.base.BaseActivity
import com.ozan.cleanpokedex.ui.list.PokemonListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActMainBinding>() {

    override val layoutRes= R.layout.act_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.replace(R.id.frmMain, PokemonListFragment.new())
        }
    }

}