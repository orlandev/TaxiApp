package com.orlandev.taxiapp.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextTitle(
    title: String,
    subtitle: String,
    subtitleWidth: Dp = 120.dp,
    middleContent: @Composable () -> Unit = {}
) {

    Column {
        Text(
            text = title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge
        )
        middleContent()
        Text(
            text = subtitle,
            maxLines = 2,
            modifier = Modifier.width(subtitleWidth),
            style = MaterialTheme.typography.labelLarge,
            letterSpacing = 1.sp,
            color = Color.White.copy(alpha = 0.6f),
        )
    }

}