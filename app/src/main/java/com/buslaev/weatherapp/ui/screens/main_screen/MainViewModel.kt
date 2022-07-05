package com.buslaev.weatherapp.ui.screens.main_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buslaev.weatherapp.domain.models.WeatherInfo
import com.buslaev.weatherapp.domain.repository.WeatherRepository
import com.buslaev.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainScreenState(
    var isLoading: Boolean = true,
    var error: String = "",
    var weatherInfo: WeatherInfo? = null
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    var uiState by mutableStateOf(MainScreenState())
        private set

    init {
        getTemperatures()
    }

    private fun getTemperatures() = viewModelScope.launch(Dispatchers.IO) {
        val data = repository.getTemperatures(
            lat = 54.99104657940679,
            lon = 82.85667844099297
        )
        when (data) {
            is Resource.Loading -> {
                uiState = uiState.copy(
                    isLoading = true
                )
            }
            is Resource.Error -> {
                uiState = uiState.copy(
                    error = data.message ?: ""
                )
            }
            is Resource.Success -> {
                uiState = uiState.copy(
                    isLoading = false,
                    weatherInfo = data.data
                )
            }
        }
    }
}