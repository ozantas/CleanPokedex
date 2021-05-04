package com.ozan.cleanpokedex.extension

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

fun <T : ViewDataBinding> ViewGroup.inflateBinding(@LayoutRes layoutId: Int): T {
    return DataBindingUtil.inflate(context.layoutInflater(), layoutId, this, false)
}

fun View.hideKeyboard(activity: Activity) {
    clearFocus()
    activity.inputManager().hideSoftInputFromWindow(windowToken, 0)
}

fun View.visibleIf(boolean: Boolean, elseVisibility: Int = View.GONE) {
    visibility = if (boolean) {
        View.VISIBLE
    } else {
        elseVisibility
    }
}

fun AppCompatActivity.setupToolbar(toolbar: Toolbar) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
}

fun FragmentManager.replace(containerId: Int, fragment: Fragment, addToBackStack: Boolean = false) {
    val tag = fragment::class.java.simpleName
    beginTransaction().apply {
        if (addToBackStack) addToBackStack(tag)
        replace(containerId, fragment, tag)
    }.commitAllowingStateLoss()
}

fun FragmentManager.add(containerId: Int, fragment: Fragment, addToBackStack: Boolean = false) {
    val tag = fragment::class.java.simpleName
    beginTransaction().apply {
        if (addToBackStack) addToBackStack(tag)
        add(containerId, fragment, tag)
    }.commitAllowingStateLoss()
}

fun FragmentManager.remove(tag: String) {
    findFragmentByTag(tag)?.let {
        beginTransaction().apply {
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
        }.remove(it).commitAllowingStateLoss()
    }
}

fun AppCompatActivity.replace(
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.replace(containerId, fragment, addToBackStack)
}