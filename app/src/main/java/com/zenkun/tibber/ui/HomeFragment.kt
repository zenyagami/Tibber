package com.zenkun.tibber.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zenkun.domain.model.PowerUpModel
import com.zenkun.tibber.common.HomeViewModel
import com.zenkun.tibber.common.theme.TibberTheme
import com.zenkun.tibber.ui.compose.PowerUpsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

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
                    PowerUpsScreen(
                        viewModel = viewModel,
                        onItemClicked = { displayDeviceDetails(it) }
                    )
                }
            }
        }
    }

    private fun displayDeviceDetails(powerUpModel: PowerUpModel) {
        HomeFragmentDirections.actionHomeFragmentToPowerUpDeviceDetailsFragment(
            powerUpModel
        ).also { findNavController().navigate(it) }
    }
}
