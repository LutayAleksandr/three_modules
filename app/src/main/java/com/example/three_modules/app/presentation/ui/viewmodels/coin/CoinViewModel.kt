package com.example.three_modules.app.presentation.ui.viewmodels.coin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.three_modules.app.presentation.ui.retrofit.Common
import com.example.three_modules.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CoinViewModel: ViewModel() {

    private val _sharedViewEffects = MutableSharedFlow<SharedViewEffects>()
    val sharedViewEffects: SharedFlow<SharedViewEffects> = _sharedViewEffects.asSharedFlow()

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        getPriceVariations()
    }

    private fun getPriceVariations(){
        viewModelScope.launch { // 1
            for (i in 1..100) { // 2
                delay(1500) // 3
                _sharedViewEffects.emit(SharedViewEffects.PriceVariation(i)) // 4
            }
        }
    }

    fun getCoin() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = Common.retrofitService.getCoinList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

//    fun requestCoinList() {
//        if (viewState.value.coins.isNotEmpty()) return
//
//        viewModelScope.launch {
//            when (val result = coinsRepository.getCoins()) {
//                is Result.Success -> handleCoinList(result.value)
//                is Result.Failure -> handleFailure(result.cause)
//            }
//        }
//    }
}