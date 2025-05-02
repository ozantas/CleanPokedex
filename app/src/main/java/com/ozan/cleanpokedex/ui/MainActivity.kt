package com.ozan.cleanpokedex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.ozan.cleanpokedex.extension.ui
import com.ozan.cleanpokedex.ui.navigation.MainNavigation
import com.ozan.cleanpokedex.ui.theme.backgroundDark
import com.ozan.cleanpokedex.ui.theme.backgroundLight
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.auto(
                backgroundLight.toArgb(),
                backgroundDark.toArgb(),
            ),
        )

        super.onCreate(savedInstanceState)
        ui {
            val navController = rememberNavController()
            MainNavigation(navController)
        }
    }

}