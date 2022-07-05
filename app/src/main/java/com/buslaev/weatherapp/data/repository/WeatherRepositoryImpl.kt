package com.buslaev.weatherapp.data.repository

import com.buslaev.weatherapp.data.mapper.WeatherMapper
import com.buslaev.weatherapp.data.models.TemperatureResponse
import com.buslaev.weatherapp.data.remote.WeatherApi
import com.buslaev.weatherapp.domain.models.Weather
import com.buslaev.weatherapp.domain.models.WeatherInfo
import com.buslaev.weatherapp.domain.repository.WeatherRepository
import com.buslaev.weatherapp.domain.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val mapper: WeatherMapper
) : WeatherRepository {

    override suspend fun getTemperatures(lat: Double, lon: Double): Resource<WeatherInfo> {
        return try {
            val data = api.getTemperatures(latitude = lat, longitude = lon)
            val weather = mapper.toWeatherInfo(data)
            Resource.Success(
                data = weather
            )
        } catch (e: Exception) {
            Resource.Error(message = e.message ?: "An unknown error occurred.")
        }
    }
}