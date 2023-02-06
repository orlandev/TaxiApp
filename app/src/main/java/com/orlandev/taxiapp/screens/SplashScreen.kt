package com.orlandev.taxiapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.orlandev.taxiapp.R
import com.orlandev.taxiapp.ui.theme.TaxiAppTheme
import com.orlandev.taxiapp.ui.theme.TaxiBlack200
import com.orlandev.taxiapp.ui.theme.TaxiGray
import com.orlandev.taxiapp.ui.theme.TaxiYellow

//TODO REVIEW
val hPadding = 20.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen() {

    //Usaremos un Scaffold, de esta manera el FabButton queda en un lugar estandar
    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        //Cambiaremos el FabButton de Material3 por el de Material2
        //para que se vea igual al diseño original
        androidx.compose.material.FloatingActionButton(
            backgroundColor = TaxiYellow,
            onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
        }
    }) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AsyncImage(
                model = R.drawable.background,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.fillMaxSize()) {
                //Text
                Spacer(modifier = Modifier.height(40.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = hPadding)
                        .padding(top = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.welcome),
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = TaxiBlack200,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = stringResource(id = R.string.subtitle),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = TaxiGray
                    )
                }

                //Imagen Central

                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = R.drawable.middle_image,
                    contentDescription = null
                )

                //Footer Text
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = hPadding)
                        .padding(top = 10.dp),
                    text = stringResource(id = R.string.splash_message),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    letterSpacing = 1.sp,
                    lineHeight = 28.sp,
                    style = MaterialTheme.typography.bodyMedium
                )

            }

        }
    }


}

@Preview()
@Composable
fun SplashScreenPreview() {
    TaxiAppTheme {
        SplashScreen()
    }
}