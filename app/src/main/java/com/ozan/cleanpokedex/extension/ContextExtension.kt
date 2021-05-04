package com.ozan.cleanpokedex.extension

import android.app.Activity
import android.content.*
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatDelegate
import com.ozan.cleanpokedex.data.def.ThemeSelection

inline fun <reified T : Activity> Context.launchActivity() {
    startActivity(
        Intent(this, T::class.java)
    )
}

fun Context.openMarketPage() {
    try {
        val uri = Uri.parse("market://details?id=$packageName")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    } catch (anfe: ActivityNotFoundException) {
        val uri = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}

fun Context.layoutInflater() = LayoutInflater.from(this)

fun ViewGroup.layoutInflater() = LayoutInflater.from(context)

fun Activity.inputManager(): InputMethodManager {
    return applicationContext.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
}

fun ThemeSelection.set() {
    val mode= when (this) {
        ThemeSelection.LIGHT ->
            AppCompatDelegate.MODE_NIGHT_NO
        ThemeSelection.DARK ->
            AppCompatDelegate.MODE_NIGHT_YES
        ThemeSelection.SYSTEM ->
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }
    AppCompatDelegate.setDefaultNightMode(mode)
}