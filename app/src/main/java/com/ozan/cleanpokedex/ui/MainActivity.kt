package com.ozan.cleanpokedex.ui

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.ozan.cleanpokedex.extension.ui
import com.ozan.cleanpokedex.ui.navigation.MainNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        ui {
            val navController = rememberNavController()
            Scaffold(
                modifier = Modifier.fillMaxWidth()
            ) { innerPadding ->
                Column(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    MainNavigation(navController)
                }
            }
        }
    }

}