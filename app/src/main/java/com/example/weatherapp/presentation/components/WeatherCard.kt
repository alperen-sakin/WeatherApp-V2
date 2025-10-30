package com.example.weatherapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.WeatherData
import com.example.weatherapp.presentation.WeatherState
import com.example.weatherapp.presentation.components.states.WeatherDataDisplayState
import com.example.weatherapp.ui.theme.Poppins
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    var currentTime by rememberSaveable {
        mutableStateOf(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
        )
    }

    LaunchedEffect(key1 = true) {
        while (true) {
            currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
            delay(MINUTE_IN_MILLIS)
        }
    }

    state.weatherInfo?.currentWeatherData?.let { data ->

        Card(
            modifier = modifier.padding(16.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TopSection(state, currentTime)

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.height(150.dp)

                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "${data.temperature}°C",
                    fontSize = 50.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = data.weatherType.weatherDesc,
                    fontSize = 15.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(32.dp))

                WeatherDAtaDisplays(data)
            }
        }
    }
}

@Composable
private fun TopSection(
    state: WeatherState,
    currentTime: String?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state.cityName ?: "",
            fontSize = 16.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )

        Text(
            text = "Today $currentTime",
            fontSize = 16.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
    }
}

@Composable
private fun WeatherDAtaDisplays(data: WeatherData) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WeatherDataDisplay(
            weatherDataDisplayState = WeatherDataDisplayState(
                value = data.pressure.toInt(),
                unit = "hpa",
                icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                iconTint = Color.White,
                textStyle = TextStyle(color = Color.White)
            )
        )

        WeatherDataDisplay(
            weatherDataDisplayState = WeatherDataDisplayState(
                value = data.humidity.toInt(),
                unit = "%",
                icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                iconTint = Color.White,
                textStyle = TextStyle(color = Color.White)
            )
        )

        WeatherDataDisplay(
            weatherDataDisplayState = WeatherDataDisplayState(
                value = data.windSpeed.toInt(),
                unit = "km/h",
                icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                iconTint = Color.White,
                textStyle = TextStyle(color = Color.White)
            )

        )
    }
}

private const val MINUTE_IN_MILLIS = 60000L
