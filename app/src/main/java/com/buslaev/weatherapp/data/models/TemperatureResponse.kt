package com.buslaev.weatherapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TemperatureResponse(
    @SerialName("hourly") val hourly: Hourly
)

@Serializable
data class Hourly(
    @SerialName("time") val time: List<String>,
    @SerialName("weathercode") val code: List<Int>,
    @SerialName("temperature_2m") val temperatures: List<Float>
)
