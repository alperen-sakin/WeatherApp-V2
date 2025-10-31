package com.example.weatherapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.presentation.WeatherState
import com.example.weatherapp.ui.theme.ForecastColor
import com.example.weatherapp.ui.theme.ForecastCurrentDayColor
import com.example.weatherapp.ui.theme.Poppins
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->

        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.today),
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
            )

            LazyRow(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)

            ) {
                items(data) { weatherData ->
                    ForecastItem(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(148.dp)
                            .padding(horizontal = 10.dp, vertical = 16.dp),
                        itemColor = if (weatherData.time.hour == LocalDateTime.now().hour) {
                            ForecastCurrentDayColor
                        } else {
                            ForecastColor
                        }

                    )
                }
            }
        }
    }
}
