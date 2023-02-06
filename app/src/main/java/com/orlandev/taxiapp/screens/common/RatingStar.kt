package com.orlandev.taxiapp.screens.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orlandev.taxiapp.ui.theme.TaxiYellow


@Composable
fun RatingStar(rating:Double) {

    Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$rating",
            color = TaxiYellow,
            fontSize = 16.sp,
            style = MaterialTheme.typography.labelMedium
        )
        Icon(
            modifier = Modifier.padding(start = 2.dp),
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = TaxiYellow
        )

    }

}
