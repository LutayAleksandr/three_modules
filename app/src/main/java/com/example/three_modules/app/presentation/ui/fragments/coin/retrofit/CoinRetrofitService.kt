package com.example.three_modules.app.presentation.ui.fragments.coin.retrofit

import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinJsonMain
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinRetrofitService {

    @GET("name")
    suspend fun getThreeCoinsRetrofit(@Path("name") coinID: String): List<CoinJsonMain>
}

var retrofit = Retrofit.Builder()
    .baseUrl("https://api.coingecko.com/api/v3/coins/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var restThreeCoinsApi = retrofit.create(CoinRetrofitService::class.java)