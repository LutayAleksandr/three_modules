package com.example.three_modules.app.data

import android.content.Context
import com.example.three_modules.app.extensions.getTownsFromAssetsForWeather
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.weather.models.toRVItemModel
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    val context: Context
) {
    fun getAllMappedCitiesForWeather(): List<WeatherRVItemModel> {
        return context.getTownsFromAssetsForWeather()
            .mapIndexed { index, weatherJsonModel -> weatherJsonModel.toRVItemModel(index = index) }
    }

}