package com.example.three_modules.app.presentation.ui.retrofit

import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinJsonMain
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinJsonURLModel
import retrofit2.http.GET

interface RetrofitService {

    @GET("markets?vs_currency=usd&order=market_cap_desc&")
    suspend fun getCoinList(): List<CoinJsonURLModel>

}

