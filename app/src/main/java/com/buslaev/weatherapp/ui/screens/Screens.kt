package com.buslaev.weatherapp.ui.screens

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object FutureWeatherScreen : Screens("future_weather_screen")
}
