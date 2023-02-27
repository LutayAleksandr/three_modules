package com.example.three_modules.app.presentation.ui.fragments.coin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.selection.SelectionTracker
import com.example.three_modules.R
import com.example.three_modules.app.data.CoinRepository
import com.example.three_modules.app.presentation.ui.fragments.coin.database.CoinsDao
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinEntity
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.coin.models.toRVItemModel
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
    var coinsList = listOf<CoinRVItemModel>()
    var coinsListEntity = listOf<CoinEntity>()



    var selectedCoinList = mutableListOf<CoinRVItemModel>()

    @Inject
    lateinit var coinDao: CoinsDao


    fun getAllCoin() {
        viewModelScope.launch {
            repository.loadCoins()
            coinsListEntity = repository.getAllCoins()
            coinsList = coinsListEntity.mapIndexed{ index, coinEntity ->  coinEntity.toRVItemModel(index = index)}
            _coins.emit(value = coinsList)
            getSelectedCoins()
        }
    }

    fun loadCoins() {
        viewModelScope.launch {
            repository.loadCoins()
            repository.getAllCoins()
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
            var selectedTitleList = ""
            selectedCoinList.forEach { selected ->
                selectedTitleList += "${selected.name}  "
                coinsList.find {
                    it.name == selected.name
                }?.isSelected = true
            }
            _selectedTitle.emit(selectedTitleList)
            _coins.emit(coinsList)
        }
    }

    suspend fun saveSelectedCoins() {
        coinsList.forEach { item ->
            repository.updateAllCoins(
                id = item.id,
                isSelected = false,
            )
        }
        selectedCoinList.forEachIndexed { index, item ->
            repository.updateCoin(
                id = item.id,
                isSelected = item.isSelected,
                selectedPosition = index
            )
        }
    }



    fun getSelectedCoins(callback: ((list: List<CoinRVItemModel>) -> Unit)? = null) {
        var ids = selectedCoinList.joinToString(",") { it.id }
        viewModelScope.launch {
            selectedCoinList = repository.getAllCoins().filter {
                it.isSelected
            }.mapIndexed { index, coinEntity -> coinEntity.toRVItemModel(index = index) }.toMutableList()
            callback?.invoke(selectedCoinList)
        }
    }

//    fun getSelectedCoinsToMain(): List<CoinRVItemModel> {
//        val listSelected = repository.getAllCoins().filter {
//            it.isSelected
//        }.mapIndexed { index, coinEntity -> coinEntity.toRVItemModel(index = index) }
//        return listSelected
//    }
}

