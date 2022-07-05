package com.buslaev.weatherapp.domain.models

import java.time.LocalDateTime

data class WeatherInfo(
    val currentWeatherData: Weather,
    val weathers: List<Weather>
)

data class Weather(
    val index: Int,
    val weatherData: WeatherData
)

data class WeatherData(
    val time: LocalDateTime,
    val code: Int,
    val temperature: Float
)
