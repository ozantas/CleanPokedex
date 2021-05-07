package com.ozan.cleanpokedex.ui.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozan.cleanpokedex.ui.util.VerticalSpacingItemDecoration

@BindingAdapter(value = ["verticalItemSpace"])
fun RecyclerView.bindVerticalSpace(space: Int?) {
    if (space == null) return
    val verticalSpaceDivider = VerticalSpacingItemDecoration(space)
    addItemDecoration(verticalSpaceDivider)
}
