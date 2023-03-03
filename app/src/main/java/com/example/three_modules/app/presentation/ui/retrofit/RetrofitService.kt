package com.example.three_modules.app.presentation.ui.retrofit

import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinJsonURLModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&")
    suspend fun getCoinList(): List<CoinJsonURLModel>


    @GET("coins/markets")
    suspend fun getThreeCoinsRetrofit(
        @Query("vs_currency") currency: String = "usd",
        @Query("ids") ids: String = "",
    ): List<CoinJsonURLModel>

}

