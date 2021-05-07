package com.ozan.cleanpokedex.ui.bindingadapter

import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.scale
import androidx.databinding.BindingAdapter
import com.ozan.cleanpokedex.ui.uimodel.pokemon.PhysicalInfoUiModel

@BindingAdapter("pokemonId")
fun TextView.bindPokemonId(id: Int) {
    text = "#${id}"
}

@BindingAdapter("physicalInfo")
fun TextView.bindPhysicalInfo(model: PhysicalInfoUiModel?) {
    if (model == null) return
    text = SpannableStringBuilder()
        .bold {
            append(model.value.toString())
        }
        .append(" ")
        .scale(0.5f) {
            append(model.unit)
        }
}