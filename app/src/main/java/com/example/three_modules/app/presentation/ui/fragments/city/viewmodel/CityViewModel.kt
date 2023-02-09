package com.example.three_modules.app.presentation.ui.fragments.city.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.three_modules.app.data.CityRepository
import com.example.three_modules.app.presentation.ui.fragments.city.models.CityRVItemModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityViewModel @Inject constructor(
    private val repository: CityRepository,
) : ViewModel() {

    private val _cities = MutableSharedFlow<List<CityRVItemModel>>()
    val cities = _cities.asSharedFlow()

    fun getAllCities() {
        viewModelScope.launch {
            _cities.emit(value = repository.getAllMappedCities().toMutableList())
        }
    }

    fun selectedModel(item: CityRVItemModel) {
        viewModelScope.launch {
            val list = repository.getAllMappedCities().toMutableList()
            list.find {
                it.id == item.id
            }?.isSelected = !item.isSelected
            _cities.emit(list)
        }
    }

}