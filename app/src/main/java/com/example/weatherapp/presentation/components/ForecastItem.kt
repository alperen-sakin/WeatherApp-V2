package com.example.weatherapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.weatherapp.domain.model.WeatherData
import com.example.weatherapp.ui.theme.ForecastColor
import com.example.weatherapp.ui.theme.Poppins
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastItem(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    itemColor: Color = ForecastColor
) {
    val formattedTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }
    Box(
        modifier = Modifier
            .dropShadow(
                shape = RoundedCornerShape(30.dp),
                shadow = Shadow(
                    radius = 10.dp,
                    spread = 0.dp,
                    color = Color.Black.copy(QUARTER_ALPHA),
                    offset = DpOffset(
                        x = 5.dp,
                        y = 4.dp
                    )

                )
            )
            .background(
                color = itemColor,
                shape = RoundedCornerShape(30.dp),
            )
            .innerShadow(
                shape = RoundedCornerShape(30.dp),
                shadow = Shadow(
                    radius = 1.dp,
                    color = Color.White.copy(QUARTER_ALPHA)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formattedTime,
                color = textColor,
                fontFamily = Poppins
            )

            Image(
                painter = painterResource(weatherData.weatherType.iconRes),
                contentDescription = null,
                modifier = Modifier.width(44.dp)
            )

            Text(
                text = "${weatherData.temperature}°",
                color = textColor,
                fontFamily = Poppins
            )
        }
    }
}

private const val QUARTER_ALPHA = .25F
