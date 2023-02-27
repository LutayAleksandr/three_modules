package com.example.three_modules.app.presentation.ui.fragments.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.three_modules.app.data.CoinRepository
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.coin.models.toRVItemModel
import com.example.three_modules.app.presentation.ui.retrofit.Common
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class MainViewModel @Inject constructor(
    private val repository: CoinRepository,
) : ViewModel() {

    private val _coins = MutableSharedFlow<List<CoinRVItemModel>>()
    val coins = _coins.asSharedFlow()

    fun getSelectedCoins() {
        viewModelScope.launch {
            val threeCoins = repository.getAllCoins().filter {
                it.isSelected
            }
            if (threeCoins.isNotEmpty()) {
                val ids = threeCoins.joinToString(",") { it.id }
                _coins.emit(
                    Common.retrofitService.getThreeCoinsRetrofit(ids = ids)
                        .mapIndexed { index, coinJsonURLModel -> coinJsonURLModel.toRVItemModel(index = index) })
            } else {
                _coins.emit(threeCoins.mapIndexed { index, coinEntity -> coinEntity.toRVItemModel(index = index) })
            }
        }
    }
}