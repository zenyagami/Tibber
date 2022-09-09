package com.zenkun.tibber.common.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val material: ColorScheme,
    val secondary: Color,
    val cardBackgroundColor: Color
)
