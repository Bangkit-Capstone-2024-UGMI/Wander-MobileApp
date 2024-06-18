package com.bangkit.wander.app.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = AppColor.PrimaryYellow,
    secondary = AppColor.PrimaryBlue,
    tertiary = AppColor.SecondaryBlue,
    background = AppColor.Light,
    surface = Color(0xFFFFFDFB),
    onPrimary = AppColor.PrimaryDark,
    onSecondary = Color.White,
    onTertiary = AppColor.PrimaryDark,
    onBackground = AppColor.PrimaryDark,
    onSurface = AppColor.PrimaryDark,

    primaryContainer = AppColor.SecondaryYellow,
    secondaryContainer = AppColor.PrimaryDarkVariant,
    onPrimaryContainer = AppColor.PrimaryDark,
    onSecondaryContainer = AppColor.Light,
    error = Color(0xFFB00020),
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF370B1E),
    surfaceVariant = AppColor.SecondaryYellow,
    onSurfaceVariant = AppColor.PrimaryDark,
    outline = AppColor.PrimaryDarkVariant,
    inverseOnSurface = AppColor.Light,
    inverseSurface = AppColor.PrimaryDark,
    inversePrimary = AppColor.SecondaryBlue
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primaryContainer.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}