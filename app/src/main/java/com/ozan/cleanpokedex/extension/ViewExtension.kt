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
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
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
import com.ozan.cleanpokedex.ui.theme.PokedexTheme


inline fun ComponentActivity.ui(
    crossinline content: @Composable () -> Unit
) {
    setContent {
        PokedexTheme {
            content.invoke()
        }
    }
}

inline fun Fragment.ui(
    crossinline content: @Composable () -> Unit
): ComposeView {
    return ComposeView(requireContext()).apply {
        setContent {
            PokedexTheme {
                content.invoke()
            }
        }
    }
}

fun <T : ViewDataBinding> ViewGroup.inflateBinding(@LayoutRes layoutId: Int): T {
    return DataBindingUtil.inflate(context.layoutInflater(), layoutId, this, false)
}

fun View.hideKeyboard(activity: Activity) {
    clearFocus()
    activity.inputManager().hideSoftInputFromWindow(windowToken, 0)
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