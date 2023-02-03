package com.example.three_modules.app.data

import android.content.Context
import com.example.three_modules.app.extensions.getTownsFromAssets
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityJsonModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.toRVItemModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject

class CityRepository @Inject constructor(
    val context: Context
) {

    fun getAllMappedCities(): List<CityRVItemModel> {
        return context.getTownsFromAssets()
            .mapIndexed { index, cityJsonModel -> cityJsonModel.toRVItemModel(index = index) }
    }

}