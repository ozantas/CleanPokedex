package com.ozan.cleanpokedex.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.ozan.cleanpokedex.BR

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    protected var binding: DB? = null
    protected abstract val viewModel: ViewModel
    abstract val getLayoutId: Int

    val fragmentTag= this::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, getLayoutId, container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.setVariable(BR.viewModel,viewModel)
        return binding?.root
    }

    override fun onDestroy() {
        binding= null
        super.onDestroy()
    }

}