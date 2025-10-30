package com.example.weatherapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.R
import com.example.weatherapp.presentation.WeatherState

@Composable
fun WeatherScreen(
    state: WeatherState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(
                id =
                if (state.isDay) R.drawable.background_day else R.drawable.background_night
            ),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()

        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            WeatherCard(
                state = state,
                backgroundColor = Color.DarkGray
            )
        }
    }
}
