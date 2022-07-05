package com.buslaev.weatherapp.data.models

import com.squareup.moshi.Json

data class TemperatureResponse(
    @field:Json(name = "hourly") val hourly: Hourly,
    @field:Json(name = "hourly_units") val hourlyUnits: HourlyUnits
)

data class Hourly(
    @field:Json(name = "time") val time: List<String>,
    @field:Json(name = "weathercode") val code: List<Int>,
    @field:Json(name = "temperature_2m") val temperatures: List<Float>,
    @field:Json(name = "windspeed_10m") val windspeed: List<Float>,
    @field:Json(name = "relativehumidity_2m") val relativehumidity: List<Int>,
    @field:Json(name = "pressure_msl") val pressureMsl: List<Float>
)

data class HourlyUnits(
    @field:Json(name = "relativehumidity_2m") val relativehumidity_2m: String,
    @field:Json(name = "pressure_msl") val pressure_msl: String,
    @field:Json(name = "windspeed_10m") val windspeed_10m: String,
    @field:Json(name = "temperature_2m") val temperature_2m: String
)
