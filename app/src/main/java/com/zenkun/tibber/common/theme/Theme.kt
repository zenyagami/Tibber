package com.zenkun.tibber.common.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        material = lightColorScheme(),
        secondary = OnSurfaceSecondary,
        cardBackgroundColor = Color.White
    )
}
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
)

private val LightColorScheme = lightColorScheme(
    primary = Blue100,
    secondary = Blue100,
    tertiary = Pink40,
    surface = GrayBackground

)

internal val LightTheme = ExtendedColors(
    material = lightColorScheme(),
    secondary = OnSurfaceSecondary,
    cardBackgroundColor = Color.White
)

internal val DarkTheme = ExtendedColors(
    material = darkColorScheme(),
    secondary = OnSurfaceSecondary,
    cardBackgroundColor = Color.White
)


@Composable
fun TibberTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    val theme = if (darkTheme) {
        DarkTheme.copy(
            material = colorScheme,
            cardBackgroundColor = MaterialTheme.colorScheme.surfaceVariant
        )
    } else {
        LightTheme.copy(material = colorScheme)
    }

    CompositionLocalProvider(LocalExtendedColors provides theme) {
        MaterialTheme(
            colorScheme = theme.material,
            typography = Typography,
            content = content
        )
    }
}

object TibberAppTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}