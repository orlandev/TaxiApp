package com.orlandev.taxiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.orlandev.taxiapp.data.FakeRepository
import com.orlandev.taxiapp.screens.BookingScreen
import com.orlandev.taxiapp.ui.theme.TaxiAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaxiAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    //  Greeting("Android")
                    // SplashScreen()
                    //RideScreen()
                    //Solo para probar
                    val fakeRepository = FakeRepository()
                    //El error esta en que nunca son los mismos
                    //Ups
                    BookingScreen(fakeRepository.getAllTaxiData().first().id)
                }
            }
        }
    }
}