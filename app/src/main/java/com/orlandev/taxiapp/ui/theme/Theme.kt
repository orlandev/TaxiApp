package com.orlandev.taxiapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Purple80, secondary = PurpleGrey80, tertiary = Pink80
)

@Composable
fun TaxiAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        //Siempre en modo oscuro
        colorScheme = DarkColorScheme, typography = Typography, content = content
    )
}