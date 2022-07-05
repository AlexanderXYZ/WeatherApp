package com.buslaev.weatherapp.data.mapper

import com.buslaev.weatherapp.data.models.Hourly
import com.buslaev.weatherapp.data.models.TemperatureResponse
import com.buslaev.weatherapp.domain.models.Weather
import com.buslaev.weatherapp.domain.models.WeatherData
import com.buslaev.weatherapp.domain.models.WeatherInfo
import com.buslaev.weatherapp.domain.models.WeatherUnits
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WeatherMapper {

    private fun Hourly.toWeather(): List<Weather> {
        val result = mutableListOf<Weather>()
        time.forEachIndexed { index, time ->
            result.add(
                index = index,
                element = Weather(
                    index = index,
                    weatherData = WeatherData(
                        time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                        code = code[index],
                        temperature = temperatures[index],
                        windspeed = windspeed[index],
                        relativehumidity = relativehumidity[index],
                        pressureMsl = pressureMsl[index]
                    )
                )
            )
        }
        return result
    }

    fun toWeatherInfo(temperatureResponse: TemperatureResponse): WeatherInfo {
        val weathers = temperatureResponse.hourly.toWeather()
        val now = LocalDateTime.now()
        val currentWeather = weathers.find {
            now.hour == it.weatherData.time.hour
        } ?: weathers[0]

        return WeatherInfo(
            currentWeatherData = currentWeather,
            weathers = weathers,
            weatherUnits = WeatherUnits(
                relativehumidityUnit = temperatureResponse.hourlyUnits.relativehumidity_2m,
                pressureMslUnit = temperatureResponse.hourlyUnits.pressure_msl,
                windspeedUnit = temperatureResponse.hourlyUnits.windspeed_10m,
                temperatureUnit = temperatureResponse.hourlyUnits.temperature_2m
            )
        )

    }
}