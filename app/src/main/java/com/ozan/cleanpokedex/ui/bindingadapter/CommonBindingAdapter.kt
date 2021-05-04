package com.ozan.cleanpokedex.ui.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter(value = ["visibleIf"])
fun View.visibleIf(updatedVisibility: Boolean?) {
    when (updatedVisibility) {
        true -> {
            if (visibility== View.VISIBLE) return
            visibility = View.VISIBLE
        }
        else -> {
            if (visibility == View.GONE) return
            visibility = View.GONE
        }
    }
}