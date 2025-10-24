package com.example.weatherapp.domain.model

import com.example.weatherapp.domain.util.WeatherType
import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperature: Double,
    val humidity: Double,
    val pressure: Double,
    val windSpeed: Double,
    val weatherType: WeatherType

)
