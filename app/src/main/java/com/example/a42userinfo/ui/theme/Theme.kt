package com.example.a42userinfo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

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

@Composable
fun CustomDialogTheme(content: @Composable () -> Unit) {
    val dialogColors = darkColorScheme(
        primary = Color.White,
        surface = Color.White.copy(alpha = 0.12f).compositeOver(Color.Black),
        onSurface = Color.White
    )

    val currentTypography = MaterialTheme.typography
    val dialogTypography = currentTypography.copy(
        bodyMedium = currentTypography.bodyMedium.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = 1.sp
        ),
        labelSmall = currentTypography.labelSmall.copy(
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.2.em
        )
    )
    MaterialTheme(colorScheme = dialogColors, typography = dialogTypography, content = content)
}