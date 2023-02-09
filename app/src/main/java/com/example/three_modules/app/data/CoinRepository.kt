package com.example.three_modules.app.data

import android.content.Context
import com.example.three_modules.app.extensions.getCoinsFromURL
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.coin.models.toRVItemModel
import javax.inject.Inject

class CoinRepository @Inject constructor(
    val context: Context
) {
    suspend fun getAllMappedCoins(): List<CoinRVItemModel> {
        return  context.getCoinsFromURL()
            .mapIndexed{ index, coinJsonURLModel ->  coinJsonURLModel.toRVItemModel(index = index)}
    }
}