package com.buslaev.weatherapp.ui.screens.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.buslaev.weatherapp.ui.screens.main_screen.components.MainWeatherCard
import com.buslaev.weatherapp.ui.screens.main_screen.components.MainWeatherTemperaturs

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.uiState
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState) {
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                MainWeatherCard(
                    currentWeather = state.weatherInfo?.currentWeatherData?.weatherData,
                    weatherUnits = state.weatherInfo?.weatherUnits,
                    dayTemperatures = listOf(26F, 24F, 28F, 20F)
                )

                Spacer(modifier = Modifier.height(8.dp))

                MainWeatherTemperaturs()
            }
        }
    }
}