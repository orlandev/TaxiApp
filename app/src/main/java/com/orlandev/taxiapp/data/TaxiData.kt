package com.orlandev.taxiapp.data

import androidx.annotation.DrawableRes
import com.orlandev.taxiapp.R

sealed class TaxiType(@DrawableRes val taxiIcon: Int, val title: String) {
    object Basic : TaxiType(R.drawable.taxi_basic, title = "Basic")
    object Lux : TaxiType(R.drawable.taxi_lux, title = "Lux")
}

data class TaxiData(
    val id: Int,
    val type: TaxiType,
    val rating: Double,
    val price: Int,
    val distance: Int,
)
