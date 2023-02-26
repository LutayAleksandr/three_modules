package com.example.three_modules.app.data

import android.content.Context
import com.example.three_modules.app.extensions.getTownsFromAssets
import com.example.three_modules.app.presentation.ui.fragments.city.database.CityDatabase
import com.example.three_modules.app.presentation.ui.fragments.city.models.CityEntity
import com.example.three_modules.app.presentation.ui.fragments.city.models.CityRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.city.models.toCityEntity
import com.example.three_modules.app.presentation.ui.fragments.city.models.toRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val context: Context,
    private val cityDatabase: CityDatabase
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private  var cities: List<CityEntity> = listOf()

    fun getAllMappedCities(): List<CityRVItemModel> {
        return context.getTownsFromAssets()
            .mapIndexed { index, cityJsonModel -> cityJsonModel.toRVItemModel(index = index) }
    }

    suspend fun loadCity() = withContext(scope.coroutineContext) {
        cities = cityDatabase.cityDao().getAllCities()
        if (cities.isEmpty()) {
            cities = context.getTownsFromAssets().mapIndexed { index, cityJsonModel ->  cityJsonModel.toCityEntity() }.toMutableList()
            cityDatabase.cityDao().insert(cities.toMutableList())
        }
    }


    fun getAllCities(): List<CityEntity> = cities

    suspend fun updateAllCities(id: Int, isSelected: Boolean) {
        cityDatabase.cityDao().updateSelection(isSelected, id)
    }

    suspend fun updateCity(id: Int, isSelected: Boolean) {
        cityDatabase.cityDao().updateSelection(isSelected,id)
    }
}