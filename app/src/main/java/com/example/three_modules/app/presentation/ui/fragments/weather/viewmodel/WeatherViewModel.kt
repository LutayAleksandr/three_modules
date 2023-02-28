package com.example.three_modules.app.presentation.ui.fragments.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.three_modules.app.data.WeatherRepository
import com.example.three_modules.app.presentation.ui.fragments.weather.datamodel.WeatherDao
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherEntity
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.weather.models.toRVItemModelFromWeatherEntity
import com.example.three_modules.app.presentation.ui.fragments.weather.states.WeatherActions
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    @Inject
    lateinit var cityDao: WeatherDao

    private val _cities = MutableSharedFlow<List<WeatherRVItemModel>>()
    val cities = _cities.asSharedFlow()
    private val _selectedTitle = MutableSharedFlow<String>()
    val selectedTitle = _selectedTitle.asSharedFlow()
    private val _action = MutableSharedFlow<WeatherActions>()
    val action = _action.asSharedFlow()

    private var citiesList = listOf<WeatherRVItemModel>()
    var citiesListEntity = listOf<WeatherEntity>()
    var list = mutableListOf<WeatherRVItemModel>()

    private val _weathers = MutableSharedFlow<List<WeatherRVItemModel>>()
    val weathers = _weathers.asSharedFlow()

    fun getAllCities() {
        viewModelScope.launch {
            repository.loadCity()
            citiesListEntity = repository.getAllCities()
            citiesList = citiesListEntity.mapIndexed { index, weatherEntity ->  weatherEntity.toRVItemModelFromWeatherEntity(index = index)}
            _cities.emit(value = citiesList)
            getSelectedCity()
        }
    }

    fun selectedModel(item: WeatherRVItemModel) {

        viewModelScope.launch {
            if (!item.isSelected) {
                list.add(item)
            }
            if (item.isSelected) {
                list.remove(item)
                item.isSelected = false
            }
            if (list.size > 1) {
                val element = list[0]
                citiesList.find {
                    it.id == element.id
                }?.isSelected = false
                list.remove(element)
            }
            var selectedTitle = "Не выбрано"
            list.forEach { selected ->
                selectedTitle = ""
                selectedTitle += "${selected.cityName}  "
                citiesList.find {
                    it.id == selected.id
                }?.isSelected = true
            }
            _selectedTitle.emit(selectedTitle)
            _cities.emit(citiesList)
        }
    }

    suspend fun saveSelectedCity() {
        citiesList.forEach { item ->
            repository.updateAllCities(
                id = item.id,
                isSelected = false
            )
        }
        list.forEach { item ->
            repository.updateCity(
                id = item.id,
                isSelected = item.isSelected
            )
        }
        _action.emit(WeatherActions.PopBackStack)
    }

    private fun getSelectedCity(callback: ((list: List<WeatherRVItemModel>) -> Unit)? = null) {
        list = repository.getAllCities().filter {
            it.isSelected
        }.mapIndexed{ index, weatherEntity ->  weatherEntity.toRVItemModelFromWeatherEntity(index = index)}.toMutableList()
        var selectedTitle = "Не выбрано"

        if (list.isEmpty()) {
            viewModelScope.launch {
                _selectedTitle.emit(selectedTitle)
            }
        } else {
            selectedTitle = list.joinToString("") { it.cityName }
            viewModelScope.launch {
                _selectedTitle.emit(selectedTitle)
            }
        }
        callback?.invoke(list)
    }

}