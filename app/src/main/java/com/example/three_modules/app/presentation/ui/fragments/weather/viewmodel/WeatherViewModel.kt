package com.example.three_modules.app.presentation.ui.fragments.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.three_modules.app.data.WeatherRepository
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherRVItemModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    private val _weathers = MutableSharedFlow<List<WeatherRVItemModel>>()
    val weathers = _weathers.asSharedFlow()

    fun getAllCities() {
        viewModelScope.launch {
            _weathers.emit(value = repository.getAllMappedCitiesForWeather().toMutableList())
        }
    }

    fun selectedModel(item: WeatherRVItemModel) {
        viewModelScope.launch {
            val list = repository.getAllMappedCitiesForWeather().toMutableList()
            list.find {
                it.id == item.id
            }?.isSelected = !item.isSelected
            _weathers.emit(list)
        }
    }

}