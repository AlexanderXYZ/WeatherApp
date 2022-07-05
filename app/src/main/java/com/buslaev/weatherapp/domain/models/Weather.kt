package com.buslaev.weatherapp.domain.models

import java.time.LocalDateTime

data class WeatherInfo(
    val currentWeatherData: Weather,
    val weathers: List<Weather>,
    val weatherUnits: WeatherUnits
)

data class Weather(
    val index: Int,
    val weatherData: WeatherData
)

data class WeatherData(
    val time: LocalDateTime,
    val code: Int,
    val temperature: Float,
    val windspeed: Float,
    val relativehumidity: Int,
    val pressureMsl: Float
)

data class WeatherUnits(
    val relativehumidityUnit: String,
    val pressureMslUnit: String,
    val windspeedUnit: String,
    val temperatureUnit: String
)
