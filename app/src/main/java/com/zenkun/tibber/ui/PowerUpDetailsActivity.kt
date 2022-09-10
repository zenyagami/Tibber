package com.zenkun.tibber.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.navArgs
import com.zenkun.tibber.common.theme.TibberTheme
import com.zenkun.tibber.ui.compose.PowerUpDetailsScreen

class PowerUpDetailsActivity : ComponentActivity() {
    private val args: PowerUpDetailsActivityArgs by navArgs()
    private val viewModel: PowerUpDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TibberTheme(
                dynamicColor = false,
                darkTheme = false
            ) {
                PowerUpDetailsScreen(
                    powerUpModel = args.powerUpDevice,
                    viewModel = viewModel,
                    onNavigationClicked = { finish() }
                )
            }
        }
    }

}