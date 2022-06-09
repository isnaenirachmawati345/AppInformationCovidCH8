package com.isna.appinformationcovid.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.than.covidapp_challengeschapter7.ui.theme.Typography

private val DarkColorPalette = darkColors (
    primary = com.than.covidapp_challengeschapter7.ui.theme.blue,
    primaryVariant = com.than.covidapp_challengeschapter7.ui.theme.darkBlue,
)

private val LightColorPalette = lightColors (
    primary = com.than.covidapp_challengeschapter7.ui.theme.blue,
    primaryVariant = com.than.covidapp_challengeschapter7.ui.theme.darkBlue,
    secondary = com.than.covidapp_challengeschapter7.ui.theme.Teal200
)

@Composable
fun Chapter8Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = com.than.covidapp_challengeschapter7.ui.theme.Shapes,
        content = content
    )
}