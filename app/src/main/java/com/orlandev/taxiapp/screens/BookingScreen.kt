package com.orlandev.taxiapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.orlandev.taxiapp.R
import com.orlandev.taxiapp.data.FakeRepository
import com.orlandev.taxiapp.screens.common.RatingStar
import com.orlandev.taxiapp.screens.common.TextTitle
import com.orlandev.taxiapp.ui.theme.TaxiDarkGray
import com.orlandev.taxiapp.ui.theme.TaxiDarkGray2
import com.orlandev.taxiapp.ui.theme.TaxiYellow
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(taxiId: Int, fakeRepository: FakeRepository = FakeRepository()) {

    //Esta logica debe ir en un ViewModel; por el momento la pondremos aqui
    val currentTaxi = remember {
        derivedStateOf {
            fakeRepository.getAllTaxiData().find { it.id == taxiId }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack, contentDescription = null
                )
            }
        }, title = {
            Text(
                text = stringResource(id = R.string.booking_title_screen),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        })
    }, bottomBar = {
        BookNowButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(hPadding), text = "Book Now"
        ) {

        }
    }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(TaxiDarkGray2),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            //Image
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .width(
                            maxWidth.minus(150.dp)
                        )
                        .height(
                            maxHeight.minus(20.dp)
                        )
                        .align(Alignment.BottomCenter),
                    model = R.drawable.taxi,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            //Taxi type

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = hPadding)
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //Text
                TextTitle(
                    title = "Taxi (${currentTaxi.value?.type?.title})",
                    subtitle = "Will be arrived in 15min.",
                    subtitleWidth = 200.dp
                )
                RatingStar(rating = currentTaxi.value?.rating ?: 0.0)
            }

            //Destiny
            DestinySelection()

            //Info
            InfoBar(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
                    .padding(horizontal = hPadding),
                distance = "${currentTaxi.value?.distance ?: 0}km",
                time = "45min",
                price = "${currentTaxi.value?.price ?: 0}"
            )
            //Time

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = hPadding)
            ) {
                Text(
                    text = "Seats and Time",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(15.dp))

                var currentCantSeats by remember {
                    mutableStateOf(1)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectableGroup(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {

                    (1..4).forEach {
                        TaxiButton(it.toString(), selected = it == currentCantSeats) {
                            //ON CLICK
                            currentCantSeats = it
                        }
                    }

                }
            }

            //Date & Time

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = hPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                PickButton(
                    modifier = Modifier.weight(1f),
                    text = "Pick Date",
                    icon = Icons.Default.CalendarMonth
                ) {

                }

                Spacer(modifier = Modifier.size(20.dp))

                PickButton(
                    modifier = Modifier.weight(1f),
                    text = "Pick Time",
                    icon = Icons.Default.AccessTime
                ) {

                }

            }
        }
    }
}

@Composable
fun TaxiButton(text: String, selected: Boolean = false, onClick: () -> Unit) {

    val backgroundColor = if (selected) Color.White else MaterialTheme.colorScheme.background
    val textColor: Color = if (!selected) Color.White else MaterialTheme.colorScheme.background


    Box(modifier = Modifier
        .size(65.dp)
        .clip(RoundedCornerShape(15.dp))
        .background(backgroundColor)
        .selectable(selected = selected,
            role = Role.Tab,
            onClick = onClick,
            indication = null,
            interactionSource = remember {
                MutableInteractionSource()
            }

        ), contentAlignment = Alignment.Center) {
        Text(
            text = text, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = textColor
        )
    }

}

@Composable
fun InfoBar(modifier: Modifier = Modifier, distance: String, time: String, price: String) {
    Box(
        modifier = modifier.then(
            Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colorScheme.background)
        ),

        ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconText(icon = Icons.Outlined.Map, text = distance)
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .padding(vertical = 10.dp),
                color = TaxiYellow.copy(alpha = 0.1f)
            )
            IconText(icon = Icons.Default.AccessTime, text = time)
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .padding(vertical = 10.dp),
                color = TaxiYellow.copy(alpha = 0.1f)
            )
            IconText(icon = Icons.Default.AttachMoney, text = price)
        }
    }
}

@Composable
fun IconText(modifier: Modifier = Modifier, icon: ImageVector, text: String) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = TaxiYellow)
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PickButton(
    modifier: Modifier = Modifier, text: String, icon: ImageVector, onClick: () -> Unit
) {
    Box(
        modifier = modifier.then(
            Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.background)
                .height(55.dp)
                .clickable {
                    onClick()
                },

            ), contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = hPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                color = TaxiDarkGray,
                fontWeight = FontWeight.Bold
            )

            Icon(icon, contentDescription = null, tint = TaxiYellow)

        }
    }

}

@Composable
fun BookNowButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    Box(
        modifier = modifier.then(
            Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(TaxiYellow)
                .height(55.dp)
                .clickable {
                    onClick()
                },

            ), contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
