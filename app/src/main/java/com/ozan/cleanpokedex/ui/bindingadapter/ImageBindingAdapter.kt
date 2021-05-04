package com.ozan.cleanpokedex.ui.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ozan.cleanpokedex.R

@BindingAdapter("imageUrl")
fun ImageView.bindImageUrl(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_pokeball)
        .into(this)
}