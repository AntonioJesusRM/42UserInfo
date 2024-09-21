package com.example.a42userinfo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val HadesCoalition = darkColorScheme(
    primary = Black,
    secondary = White,
    tertiary = HadesBackGround
)

private val VoidCoalition = darkColorScheme(
    primary = Black,
    secondary = White,
    tertiary = VoidBackGround
)

private val OlympusCoalition = darkColorScheme(
    primary = Black,
    secondary = White,
    tertiary = OlympusBackGround
)

private val DefaultCoalition = lightColorScheme(
    primary = Black,
    secondary = White,
    tertiary = White
)

@Composable
fun UserInfoTheme(
    coalition: String,
    content: @Composable () -> Unit
) {
    val colorScheme = when (coalition.lowercase()) {
        "hades" -> HadesCoalition
        "void" -> VoidCoalition
        "olympus" -> OlympusCoalition
        else -> DefaultCoalition
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}