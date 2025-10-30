package com.example.weatherapp.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.presentation.components.states.WeatherDataDisplayState

@Composable
fun WeatherDataDisplay(
    weatherDataDisplayState: WeatherDataDisplayState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = weatherDataDisplayState.icon,
            contentDescription = null,
            modifier = Modifier.size(25.dp),
            tint = weatherDataDisplayState.iconTint
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = "${weatherDataDisplayState.value}${weatherDataDisplayState.unit}",
            style = weatherDataDisplayState.textStyle

        )
    }
}
