package com.example.three_modules.app.data

import android.content.Context
import com.example.three_modules.app.extensions.getTownsFromAssets
import com.example.three_modules.app.presentation.ui.fragments.city.models.CityRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.city.models.toRVItemModel
import javax.inject.Inject

class CityRepository @Inject constructor(
    val context: Context
) {
    fun getAllMappedCities(): List<CityRVItemModel> {
        return context.getTownsFromAssets()
            .mapIndexed { index, cityJsonModel -> cityJsonModel.toRVItemModel(index = index) }
    }

}