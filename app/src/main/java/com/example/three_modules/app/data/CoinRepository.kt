package com.example.three_modules.app.data

import android.content.Context
import com.example.three_modules.app.presentation.ui.fragments.coin.database.CoinDatabase
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinEntity
import com.example.three_modules.app.presentation.ui.fragments.coin.models.toCoinEntity
import com.example.three_modules.app.presentation.ui.retrofit.Common
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val context: Context,
    private val coinDatabase: CoinDatabase
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var coins: List<CoinEntity> = listOf()

    suspend fun loadCoins() = withContext(scope.coroutineContext) {
        coins = coinDatabase.coinsDao().getAllCoins()
        if (coins.isEmpty()) {
            coins = Common.retrofitService.getCoinList().mapIndexed{ _, coinJsonURLModel ->  coinJsonURLModel.toCoinEntity()}.toMutableList()
            coinDatabase.coinsDao().insert(coins.toMutableList())
        }
    }

    suspend fun getBySelected(isSelected: Boolean){
        coinDatabase.coinsDao().getCoinsBySelection(isSelected)
    }

    suspend fun getAllCoins(): List<CoinEntity> {
        return withContext(scope.coroutineContext) {
            return@withContext coinDatabase.coinsDao().getAllCoins()
        }
    }

    suspend fun updateCoin(id: String, isSelected: Boolean, selectedPosition: Int) {
        coinDatabase.coinsDao().updateSelection(isSelected, id)
    }

    suspend fun updateAllCoins(id: String, isSelected: Boolean, ) {
        coinDatabase.coinsDao().updateSelection(isSelected, id)
    }

}