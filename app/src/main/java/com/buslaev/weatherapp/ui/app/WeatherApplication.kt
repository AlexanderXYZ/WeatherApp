package com.buslaev.weatherapp.ui.app

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.buslaev.weatherapp.ui.screens.Screens
import com.buslaev.weatherapp.ui.screens.future_weather_screen.FutureWeatherScreen
import com.buslaev.weatherapp.ui.screens.main_screen.MainScreen

@Composable
fun WeatherApplication(
    appState: AppState = rememberAppState()
) {

    if (appState.isOnline) {
        NavHost(
            navController = appState.navController,
            startDestination = Screens.MainScreen.route
        ) {
            composable(Screens.MainScreen.route) {
                MainScreen()
            }
            composable(Screens.FutureWeatherScreen.route) {
                FutureWeatherScreen()
            }
        }
    } else {
        OfflineDialog {
            appState.checkOnline()
        }
    }
}

@Composable
fun OfflineDialog(onClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = { },
        title = {

        },
        text = {

        },
        confirmButton = {
            Button(onClick = { onClick.invoke() }) {
                Text(text = "Refresh")
            }
        }
    )
}