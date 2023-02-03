package com.example.three_modules.app.presentation.ui.fragments.city.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.three_modules.app.data.CityRepository
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityRVItemModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityViewModel @Inject constructor(
    private val repository: CityRepository,
) : ViewModel() {

    private val _cities = MutableSharedFlow<List<CityRVItemModel>>()
    val cities = _cities.asSharedFlow()
    private var citiesList = mutableListOf<CityRVItemModel>()

    fun getAllCities() {
        viewModelScope.launch {
            citiesList = repository.getAllMappedCities().toMutableList()
            _cities.emit(value = citiesList)
        }
    }
//    itrImageButton
    fun selectedModel(item: CityRVItemModel) {
        
    }

}