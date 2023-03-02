package com.example.three_modules.app.presentation.ui.fragments.city.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.three_modules.app.data.CityRepository
import com.example.three_modules.app.presentation.ui.fragments.city.database.CityDao
import com.example.three_modules.app.presentation.ui.fragments.city.models.CityEntity
import com.example.three_modules.app.presentation.ui.fragments.city.models.CityRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.city.models.toRVItemModelFromCityEntity
import com.example.three_modules.app.presentation.ui.fragments.city.states.CitiesActions
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
    private val _action = MutableSharedFlow<CitiesActions>()
    val action = _action.asSharedFlow()

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
        _action.emit(CitiesActions.PopBackStack)
    }

    private suspend fun getSelectedCity(callback: ((list: List<CityRVItemModel>) -> Unit)? = null) {
        list = repository.getAllCities().filter {
            it.isSelected
        }.mapIndexed{ index, cityEntity ->  cityEntity.toRVItemModelFromCityEntity(index = index)}.toMutableList()
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