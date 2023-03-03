package com.example.three_modules.app.presentation.ui.retrofit

import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinJsonURLModel
import kotlinx.coroutines.flow.Flow

interface CoinApiHelper {

    fun getCoinList(): Flow<List<CoinJsonURLModel>>


//    fun getThreeCoinsError(): Flow<List<CoinJsonURLModel>>


    fun getThreeCoinsRetrofit(): Flow<List<CoinJsonURLModel>>
}