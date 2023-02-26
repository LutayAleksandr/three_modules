package com.example.three_modules.app.presentation.ui.retrofit

import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinJsonMain
import retrofit2.http.GET


object Common {
    private const val baseURL = "https://api.coingecko.com/api/v3/coins/"

    val retrofitService: RetrofitService
    get() = RetrofitCoin
        .getCoin(baseURL)
        .create(RetrofitService::class.java)

}



