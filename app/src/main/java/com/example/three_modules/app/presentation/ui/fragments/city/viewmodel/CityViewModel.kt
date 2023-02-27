package com.example.three_modules.app.presentation.ui.fragments.city.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.three_modules.app.data.CityRepository
import com.example.three_modules.app.presentation.ui.fragments.city.database.CityDao
import com.example.three_modules.app.presentation.ui.fragments.city.models.CityEntity
import com.example.three_modules.app.presentation.ui.fragments.city.models.CityRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.city.models.toRVItemModelFromCityEntity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityViewModel @Inject constructor(
    private val repository: CityRepository,
) : ViewModel() {

    @Inject
    lateinit var cityDao: CityDao

    private val _cities = MutableSharedFlow<List<CityRVItemModel>>()
    val cities = _cities.asSharedFlow()
    private val _selectedTitle = MutableSharedFlow<String>()
    val selectedTitle = _selectedTitle.asSharedFlow()
    private val _selectedCity = MutableSharedFlow<List<CityRVItemModel>>()
//    val selectedCity = _selectedCoin.asSharedFlow()

    private var citiesList = listOf<CityRVItemModel>()
    var citiesListEntity = listOf<CityEntity>()
    var list = mutableListOf<CityRVItemModel>()


    fun getAllCities() {
        viewModelScope.launch {
            repository.loadCity()
            citiesListEntity = repository.getAllCities()
            citiesList = citiesListEntity.mapIndexed { index, cityEntity ->  cityEntity.toRVItemModelFromCityEntity(index = index)}
            _cities.emit(value = citiesList)
            getSelectedCity()
        }
    }

    fun selectedModel(item: CityRVItemModel) {

        viewModelScope.launch {
            if (!item.isSelected) {
                list.add(item)
            } else {
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
            var selectedTitle = ""
            list.forEach { selected ->
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
    }

    fun getSelectedCity(): List<CityRVItemModel> {
        list = repository.getAllCities().filter {
            it.isSelected
        }.mapIndexed{ index, cityEntity ->  cityEntity.toRVItemModelFromCityEntity(index = index)}.toMutableList()

        return list
    }

}