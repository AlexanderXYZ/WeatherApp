package com.buslaev.weatherapp.domain.repository

import com.buslaev.weatherapp.domain.models.WeatherInfo
import com.buslaev.weatherapp.domain.util.Resource

interface WeatherRepository {
    suspend fun getTemperatures(lat: Double, lon: Double): Resource<WeatherInfo>
}