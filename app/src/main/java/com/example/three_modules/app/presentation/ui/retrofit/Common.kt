package com.example.three_modules.app.presentation.ui.retrofit


object Common {
    private const val baseURL = "https://api.coingecko.com/api/v3/"

    val retrofitService: RetrofitService
    get() = RetrofitCoin
        .getRetrofiClient(baseURL)
        .create(RetrofitService::class.java)

}