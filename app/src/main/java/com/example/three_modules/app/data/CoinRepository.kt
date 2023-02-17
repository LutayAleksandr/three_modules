package com.example.three_modules.app.data

import android.content.Context
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinJsonURLModel
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.coin.models.toRVItemModel
import com.example.three_modules.app.presentation.ui.retrofit.Common
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinRepository @Inject constructor(
    val context: Context
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var coins = listOf<CoinJsonURLModel>()

    suspend fun loadCoins() = withContext(scope.coroutineContext) {
        if (coins.isEmpty()) {
            coins = Common.retrofitService.getCoinList()
        }
    }

    fun getAllMappedCoins(): List<CoinRVItemModel> {
        return  coins.mapIndexed{ index, coinJsonURLModel ->  coinJsonURLModel.toRVItemModel(index = index)}
    }
}