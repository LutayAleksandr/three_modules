package com.example.three_modules.app.presentation.ui.fragments.coin.retrofit

import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinJsonURLModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinRetrofitService {

    @GET("markets?vs_currency=usd&ids={name}")
    suspend fun getThreeCoinsRetrofit(@Path("name") coinID: String): List<CoinJsonURLModel>
}