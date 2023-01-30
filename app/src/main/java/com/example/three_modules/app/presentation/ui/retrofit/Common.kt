package com.example.three_modules.app.presentation.ui.retrofit

import retrofit2.create

object Common {
    private const val baseURL = "https://api.coingecko.com/api/v3/coins/"

    val retrofitService: RetrofitService
    get() = RetrofitCoin.getCoin(baseURL).create(RetrofitService::class.java)
}