package com.orlandev.taxiapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orlandev.taxiapp.ui.theme.TaxiGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            //Destino y Mapa -> Columna
            Column(modifier = Modifier.fillMaxSize()) {
                //Destino
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = hPadding),
                    border = BorderStroke(1.dp, color = TaxiGray)

                ) {

                    Text(text = "Ride")

                }


                //Mapa
            }

            //Taxi Lista
        }
    }
}