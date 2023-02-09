package com.example.three_modules.app.extensions

import android.content.Context
import com.example.three_modules.app.presentation.ui.fragments.city.models.CityJsonModel
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinJsonURLModel
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherJsonModel
import com.example.three_modules.app.presentation.ui.retrofit.Common
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun Context.getTownsFromAssets(): List<CityJsonModel> {
    val cities: List<CityJsonModel>
    try {
        cities = Gson().fromJson(
            this.assets.open("towns.json").bufferedReader().use { it.readText() },
            object : TypeToken<List<CityJsonModel>>() {}.type
        )
    } catch (exception: IOException) {
        exception.printStackTrace()
        return emptyList()
    }
    return cities
}

fun Context.getTownsFromAssetsForWeather(): List<WeatherJsonModel> {
    val cities: List<WeatherJsonModel>
    try {
        cities = Gson().fromJson(
            this.assets.open("towns.json").bufferedReader().use { it.readText() },
            object : TypeToken<List<WeatherJsonModel>>() {}.type
        )
    } catch (exception: IOException) {
        exception.printStackTrace()
        return emptyList()
    }
    return cities
}

suspend fun Context.getCoinsFromURL(): List<CoinJsonURLModel> {

    return Common.retrofitService.getCoinList()

}