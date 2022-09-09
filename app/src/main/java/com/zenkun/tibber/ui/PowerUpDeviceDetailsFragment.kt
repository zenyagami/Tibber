package com.zenkun.tibber.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.zenkun.tibber.common.theme.TibberTheme
import com.zenkun.tibber.ui.compose.PowerUpDetailsScreenContent

class PowerUpDeviceDetailsFragment : Fragment() {
    private val args: PowerUpDeviceDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
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
}
