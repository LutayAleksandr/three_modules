package com.example.three_modules.app.data

import android.content.Context
import com.example.three_modules.app.extensions.getTownsFromAssetsForWeather
import com.example.three_modules.app.presentation.ui.fragments.weather.datamodel.WeatherDatabase
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherEntity
import com.example.three_modules.app.presentation.ui.fragments.weather.models.toWeatherEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    val context: Context,
    val weatherDatabase: WeatherDatabase
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private  var cities: List<WeatherEntity> = listOf()


    suspend fun loadCity() = withContext(scope.coroutineContext) {
        cities = weatherDatabase.weatherDao().getAllWeather()
        if (cities.isEmpty()) {
            cities = context.getTownsFromAssetsForWeather().mapIndexed { _, weatherJsonModel -> weatherJsonModel.toWeatherEntity()}
            weatherDatabase.weatherDao().insert(cities.toMutableList())
        }
    }


    fun getAllCities(): List<WeatherEntity> = cities

    suspend fun updateAllCities(id: Int, isSelected: Boolean) {
        weatherDatabase.weatherDao().updateSelection(isSelected, id)
    }

    suspend fun updateCity(id: Int, isSelected: Boolean) {
        weatherDatabase.weatherDao().updateSelection(isSelected,id)
    }

}