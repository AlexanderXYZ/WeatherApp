package com.buslaev.weatherapp.di

import com.buslaev.weatherapp.core.CoreData.BASE_URL
import com.buslaev.weatherapp.data.remote.WeatherApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(
        client: OkHttpClient
    ): WeatherApi {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClien(): OkHttpClient {
        return OkHttpClient()
    }
}