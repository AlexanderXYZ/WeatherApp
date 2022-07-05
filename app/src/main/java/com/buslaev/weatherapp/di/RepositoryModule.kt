package com.buslaev.weatherapp.di

import com.buslaev.weatherapp.data.mapper.WeatherMapper
import com.buslaev.weatherapp.data.remote.WeatherApi
import com.buslaev.weatherapp.data.repository.WeatherRepositoryImpl
import com.buslaev.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: WeatherApi
    ): WeatherRepository = WeatherRepositoryImpl(api = api, mapper = WeatherMapper())
}