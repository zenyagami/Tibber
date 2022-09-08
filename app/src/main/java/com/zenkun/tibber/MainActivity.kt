package com.zenkun.tibber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zenkun.tibber.common.theme.TibberTheme
import com.zenkun.tibber.ui.compose.PowerUpsScreenContent
import com.zenkun.tibber.ui.compose.getMockedPowerUpList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TibberTheme(
                dynamicColor = false,
                darkTheme = false
            ) {
                PowerUpsScreenContent(
                    isLoading = false,
                    powerUpList = getMockedPowerUpList()
                        .shuffled()
                )
            }
        }
    }
}
