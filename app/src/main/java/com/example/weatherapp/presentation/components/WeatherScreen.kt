package com.example.weatherapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
                backgroundColor = Color.Black.copy(alpha = HALF_ALPHA)
            )

            Spacer(modifier = Modifier.height(52.dp))

            ForeCastSection(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                backgroundColor = Color.Black.copy(HALF_ALPHA),
                state = state
            )
        }
    }
}

private const val HALF_ALPHA = .5f
