package com.example.three_modules.app.presentation.ui.fragments.city.states

import com.example.three_modules.app.presentation.ui.fragments.city.models.CityRVItemModel

sealed class CityStates {
    object Empty: CityStates()
    data class Success(val list: List<CityRVItemModel>): CityStates()
}