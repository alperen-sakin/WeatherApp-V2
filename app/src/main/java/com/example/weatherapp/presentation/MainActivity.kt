package com.example.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.error
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // ViewModel'i Hilt ile enjekte et
    private val viewModel: WeatherViewModel by viewModels()

    // Konum izni istemek için bir launcher tanımla
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            // İzinler verildikten sonra veriyi yükle
            viewModel.loadWeatherInfo()
        }

        // Gerekli izinleri iste
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                val state by viewModel.state.collectAsState()

                // LaunchedEffect, state her değiştiğinde bu bloğu çalıştırmaz,
                // sadece ekrana ilk girdiğinde ve state nesnesi değiştiğinde çalışır.
                // Log basmak için idealdir.
                LaunchedEffect(state) {
                    Log.d("MainActivity", "Current State: $state")
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black) // Arka plan rengi ekleyelim
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    // Veri başarılı bir şekilde geldiyse
                    state.weatherInfo?.let { weatherInfo ->
                        // Başarılı olduğunda konsola log basabiliriz
                        Log.d("deneme", "SUCCESS! Data received: ${weatherInfo.weatherDataPerDay}")
                        // Burada gelen veriyi UI'da gösterecek composable'ı çağırabilirsin
                        // Örnek: WeatherCard(state = state, ...)
                        for (data in weatherInfo.weatherDataPerDay) {
                            Log.d(
                                "deneme",
                                "SUCCESS! Data received: ${data.key + 1}.Gun ${state.cityName}"
                            )
                        }
                    }
                }
            }
        }
    }
}
