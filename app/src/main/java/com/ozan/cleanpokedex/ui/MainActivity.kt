package com.ozan.cleanpokedex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ozan.cleanpokedex.extension.ui
import com.ozan.cleanpokedex.ui.navigation.MainNavigation
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        ui {
            val navController = rememberAnimatedNavController()
            Scaffold(
                modifier = Modifier.fillMaxWidth()
            ) { innerPadding ->
                BoxWithConstraints(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    MainNavigation(navController)
                }
            }
        }
    }

}