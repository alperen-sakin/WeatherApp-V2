package com.example.weatherapp.presentation.components.states

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle

data class WeatherDataDisplayState(
    val value: Int,
    val unit: String,
    val icon: ImageVector,
    val iconTint: Color,
    val textStyle: TextStyle = TextStyle()
)
