package com.buslaev.weatherapp.data.remote

import com.buslaev.weatherapp.data.models.TemperatureResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/forecast?hourly=temperature_2m,weathercode")
    suspend fun getTemperatures(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): TemperatureResponse
}