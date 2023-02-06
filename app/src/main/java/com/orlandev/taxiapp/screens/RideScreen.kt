package com.orlandev.taxiapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.orlandev.taxiapp.R
import com.orlandev.taxiapp.data.FakeRepository
import com.orlandev.taxiapp.data.TaxiData
import com.orlandev.taxiapp.screens.common.RatingStar
import com.orlandev.taxiapp.screens.common.TextTitle
import com.orlandev.taxiapp.ui.theme.*
import com.orlandev.taxiapp.utils.darkMapStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideScreen(fakeRepository: FakeRepository = FakeRepository()) {

    //Con esto evitamos el rerenderizado
    val listOfTaxi = remember {
        derivedStateOf {
            fakeRepository.getAllTaxiData()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        }, title = {
            Text(text = "Let's Ride", fontWeight = FontWeight.Bold)
        }, actions = {
            Image(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .clickable { },
                painter = painterResource(id = R.drawable.user),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(hPadding))
        })
    }) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            //Destino y Mapa -> Columna

            var loadingMap by remember {
                mutableStateOf(true)
            }

            Column(modifier = Modifier.fillMaxSize()) {
                //Destino
                DestinySelection()
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    GoogleMap(modifier = Modifier.fillMaxSize(), properties = MapProperties(
                        mapStyleOptions = MapStyleOptions(darkMapStyle)
                    ), onMapLoaded = {
                        loadingMap = false
                    })

                    if (loadingMap) {
                        CircularProgressIndicator()
                    }
                }

            }

            //Taxi Lista

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 26.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                //Dejamos un espacio entre el primer elemento y el borde del dispositivo
                item {
                    Spacer(modifier = Modifier.size(10.dp))
                }

                items(listOfTaxi.value) { currentTaxiData ->
                    SimpleCard(currentTaxiData) {
                        //TODO ONCLICK
                    }
                }

                //Dejamos un espacio entre el ultimo elemento y el borde del dispositivo
                item {
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }

        }
    }
}

@Composable
fun SimpleCard(currentTaxiData: TaxiData, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(220.dp)
            .padding(2.dp)
    ) {

        //Fondo de la tarjeta
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(end = 35.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(TaxiBlack)
            .clickable {
                onClick()
            })
        //Taxi icon
        Image(
            modifier = Modifier
                .height(160.dp)
                .width(70.dp)
                .align(Alignment.CenterEnd),
            painter = painterResource(id = currentTaxiData.type.taxiIcon),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 12.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {

            TextTitle(
                title = "Taxi (${currentTaxiData.type.title})",
                subtitle = "Will be arrived in 15min."
            ) {
                RatingStar(rating = currentTaxiData.rating)
            }

            Text(
                text = "${currentTaxiData.price}$/1mi",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )

        }
    }
}


@Composable
fun DestinySelection() {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = hPadding),
        border = BorderStroke(1.dp, color = TaxiGray)

    ) {

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(4f)
            ) {
                SelectionCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            /*TODO  Cambiar dirección*/
                        }, charValue = "A"
                )

                SelectionCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            /*TODO  Cambiar dirección*/
                        }, charValue = "B"
                )
            }

            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.SwapVert, contentDescription = null)
            }

        }
    }
}

@Composable
fun SelectionCard(
    modifier: Modifier = Modifier, charValue: String, direction: String = "Test Direction"
) {
    Row(
        modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(TaxiDarkGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                fontSize = 12.sp,
                text = charValue,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                text = stringResource(id = com.orlandev.taxiapp.R.string.pickup),
                color = TaxiDarkGray
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                text = direction,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelMedium,
                letterSpacing = 1.sp
            )
        }

    }
}

@Preview
@Composable
fun SelectionCardPreview() {
    TaxiAppTheme {
        SelectionCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), charValue = "A"
        )
    }
}