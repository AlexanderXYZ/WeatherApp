package com.buslaev.weatherapp.data.remote

import com.buslaev.weatherapp.data.models.TemperatureResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast?hourly=temperature_2m,relativehumidity_2m,pressure_msl,weathercode,windspeed_10m")
    suspend fun getTemperatures(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): TemperatureResponse
}