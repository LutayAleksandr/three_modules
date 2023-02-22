package com.example.three_modules.app.presentation.ui.fragments.main.viewmodel

import androidx.lifecycle.ViewModel
import com.example.three_modules.app.data.CoinRepository
import javax.inject.Inject

open class MainViewModel@Inject constructor(
    private val repository: CoinRepository,
) : ViewModel() {

    fun getSelectedCoins() {
        val list = repository.getAllCoins().filter {
            it.isSelected
        }
    }
}