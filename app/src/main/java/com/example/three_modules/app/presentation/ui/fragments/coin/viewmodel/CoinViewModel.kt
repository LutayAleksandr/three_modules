package com.example.three_modules.app.presentation.ui.fragments.coin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.three_modules.app.data.CoinRepository
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val repository: CoinRepository,
) : ViewModel() {

    private val _coins = MutableSharedFlow<List<CoinRVItemModel>>()
    val coins = _coins.asSharedFlow()

    fun getAllCoin() {
        viewModelScope.launch {
            _coins.emit(value = repository.getAllMappedCoins().toMutableList())
        }
    }

    fun selectedModel(item: CoinRVItemModel) {
        viewModelScope.launch {
            val list = repository.getAllMappedCoins().toMutableList()
            list.find {
                it.name == item.name
            }?.isSelected = !item.isSelected
            _coins.emit(list)
        }
    }


    fun getCoin() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getAllMappedCoins()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}