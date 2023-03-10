package com.example.three_modules.app.presentation.ui.retrofit

import kotlinx.coroutines.flow.flow

class CoinApiHelperImpl(private val apiService: RetrofitService): CoinApiHelper {


    override fun getCoinList() = flow {
        emit(apiService.getCoinList())
    }

    override fun getThreeCoinsRetrofit(ids: String)= flow {
        emit(apiService.getThreeCoinsRetrofit(ids = ids))
    }
}