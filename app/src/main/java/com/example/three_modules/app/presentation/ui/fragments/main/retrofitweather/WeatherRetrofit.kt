package com.example.three_modules.app.presentation.ui.fragments.main.retrofitweather

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRetrofit {
    private var retrofit: Retrofit? = null

    fun getRetrofitClient(baseUrl: String): Retrofit {
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}