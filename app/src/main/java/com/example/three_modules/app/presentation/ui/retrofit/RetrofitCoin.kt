package com.example.three_modules.app.presentation.ui.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url

object RetrofitCoin {
    private var retrofit: Retrofit? = null

    fun getCoin(baseUrl: String): Retrofit {
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}