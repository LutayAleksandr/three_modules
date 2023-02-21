package com.example.three_modules.app.presentation.ui.fragments.coin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.selection.SelectionTracker
import com.example.three_modules.app.data.CoinRepository
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val repository: CoinRepository,
) : ViewModel() {

    private val _coins = MutableSharedFlow<List<CoinRVItemModel>>()
    val coins = _coins.asSharedFlow()
    private val _selectedTitle = MutableSharedFlow<String>()
    val selectedTitle = _selectedTitle.asSharedFlow()
    private val _selectedCoin = MutableSharedFlow<List<CoinRVItemModel>>()
    val selectedCoin = _selectedCoin.asSharedFlow()
    private var tracker: SelectionTracker<Long>? = null
    var coinsList = mutableListOf<CoinRVItemModel>()

    val selectedCoinList = mutableListOf<CoinRVItemModel>()

    fun getAllCoin() {
        viewModelScope.launch {
            repository.loadCoins()
            coinsList = repository.getAllMappedCoins().toMutableList()
            _coins.emit(value = coinsList)
        }
    }

    fun countSelectedCoin(item: CoinRVItemModel) {
        viewModelScope.launch {
            if (!item.isSelected) {
                selectedCoinList.add(item)
            } else {
                selectedCoinList.remove(item)
                item.isSelected = false
            }
            if (selectedCoinList.size > 3) {
                val element = selectedCoinList[0]
                coinsList.find {
                    it.name == element.name
                }?.isSelected = false
                selectedCoinList.remove(element)
            }
            _selectedCoin.emit(
                selectedCoinList
            )
            var selectedTitle = ""
            selectedCoinList.forEach { selected ->
                selectedTitle += "${selected.name}  "
                coinsList.find {
                    it.name == selected.name
                }?.isSelected = true
            }
            _selectedTitle.emit(selectedTitle)
            _coins.emit(coinsList)
        }
    }

    fun setCoins(coinsRoom: MutableList<CoinRVItemModel>){
        selectedCoinList.clear()
        selectedCoinList.addAll(coinsRoom)
    }
}

