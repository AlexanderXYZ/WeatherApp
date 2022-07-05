package com.buslaev.weatherapp.data.mapper

import android.os.Build
import com.buslaev.weatherapp.data.models.Hourly
import com.buslaev.weatherapp.data.models.TemperatureResponse
import com.buslaev.weatherapp.domain.models.Weather
import com.buslaev.weatherapp.domain.models.WeatherData
import com.buslaev.weatherapp.domain.models.WeatherInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WeatherMapper {

    private fun Hourly.toWeather(): List<Weather> {
        val result = mutableListOf<Weather>()
        time.forEachIndexed { index, time ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                result.add(
                    index = index,
                    element = Weather(
                        index = index,
                        weatherData = WeatherData(
                            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                            code = code[index],
                            temperature = temperatures[index]
                        )
                    )
                )
            }
        }
        return result
    }

    fun toWeatherInfo(temperatureResponse: TemperatureResponse): WeatherInfo {
        val weathers = temperatureResponse.hourly.toWeather()
        val now = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val currentWeather = weathers.find {
            now.hour == it.weatherData.time.hour
        } ?: weathers[0]

        return WeatherInfo(
            currentWeatherData = currentWeather,
            weathers = weathers
        )

    }
}