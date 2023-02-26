package com.example.three_modules.app.presentation.ui.fragments.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.three_modules.app.data.CoinRepository
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinEntity
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.coin.models.toRVItemModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class MainViewModel@Inject constructor(
    private val repository: CoinRepository,
) : ViewModel() {

    private val _coins = MutableSharedFlow<List<CoinRVItemModel>>()
    val coins = _coins.asSharedFlow()



    fun getSelectedCoins(): List<CoinRVItemModel> {
        val listSelected = repository.getAllCoins().filter {
            it.isSelected
        }.mapIndexed { index, coinEntity -> coinEntity.toRVItemModel(index = index) }
        return listSelected
    }




}