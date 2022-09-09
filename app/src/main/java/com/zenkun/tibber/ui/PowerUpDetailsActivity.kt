package com.zenkun.tibber.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.navArgs
import com.zenkun.tibber.common.theme.TibberTheme
import com.zenkun.tibber.ui.compose.PowerUpDetailsScreenContent

class PowerUpDetailsActivity : ComponentActivity() {
    private val args: PowerUpDetailsActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TibberTheme(
                dynamicColor = false,
                darkTheme = false
            ) {
                PowerUpDetailsScreenContent(
                    powerUpModel = args.powerUpDevice
                )
            }
        }
    }

}