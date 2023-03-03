package com.example.three_modules.app.presentation.ui.fragments.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.three_modules.app.data.CityRepository
import com.example.three_modules.app.data.CoinRepository
import com.example.three_modules.app.data.WeatherRepository
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.coin.models.toRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.Coordinates
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.main.models.toCoordinates
import com.example.three_modules.app.presentation.ui.fragments.main.retrofitweather.WeatherApiHelper
import com.example.three_modules.app.presentation.ui.fragments.main.retrofitweather.WeatherCommon
import com.example.three_modules.app.presentation.ui.fragments.weather.models.*
import com.example.three_modules.app.presentation.ui.retrofit.CoinApiHelper
import com.example.three_modules.app.presentation.ui.retrofit.Common
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

open class MainViewModel @Inject constructor(
    private val coinRepository: CoinRepository,
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository,
    private val apiHelper: CoinApiHelper,
    private val weatherApiHelper: WeatherApiHelper
) : ViewModel() {

    private val _list = MutableSharedFlow<List<DataModel>>()
    val list = _list.asSharedFlow()

    suspend fun getSelectedCoins(callback: ((List<CoinRVItemModel>?) -> Unit)? = null) {
        val threeCoins = coinRepository.getAllCoins().filter {
            it.isSelected
        }
        if (threeCoins.isNotEmpty()) {
            viewModelScope.launch {
                apiHelper.getThreeCoinsRetrofit()
                    .flowOn(Dispatchers.IO)
                    .retry(20) {
                        delay(5000)
                        return@retry true
                    }
                    .catch { e ->
                        val coinIsEmpty = listOf<CoinRVItemModel>()
                        val listWithError = coinIsEmpty.toMutableList().also {
                            it.add(
                                CoinRVItemModel(
                                    id = "error",
                                    name = "error",
                                    imageUrl = "error",
                                    currentPrice = 1.11111.toFloat(),
                                    priceChange24h = 1.11111.toFloat(),
                                    marketCapRank = 1
                                )
                            )
                        }
                        callback?.invoke(listWithError)
                    }
                    .collect {
                        val ids = threeCoins.joinToString(",") { it.id }
                        callback?.invoke(Common.retrofitService.getThreeCoinsRetrofit(ids = ids)
                            .mapIndexed { index, coinJsonURLModel ->
                                coinJsonURLModel.toRVItemModel(
                                    index = index
                                )
                            })
                    }
            }
        } else {
            val coinIsEmpty = listOf<CoinRVItemModel>()
            callback?.invoke(coinIsEmpty)
        }
    }

    suspend fun getSelectedCityForWeather(callback: ((WeatherJsonApiModel?) -> Unit)? = null) {
        val city = weatherRepository.getAllCities().filter {
            it.isSelected
        }
        val lat = city.joinToString("") { it.latitude.toString() }
        val lon = city.joinToString("") { it.longitude.toString() }

        if (city.isNotEmpty()) {
            viewModelScope.launch {
                weatherApiHelper.getWeatherRetrofit(lat = lat, lon = lon)
                    .flowOn(Dispatchers.IO)
                    .catch { e ->
                        Log.d("ExceptionWeather", "exception: ${e.message}")
                        val listWeather = listOf<Weather>()
                        val weather = WeatherJsonApiModel(
                            weather = listWeather,
                            main = Main(
                                temp = 1.1111.toFloat(),
                                feelsLikeTemp = 1.1111.toFloat(),
                                pressure = 1.1111.toFloat(),
                                humidity = 1.1111.toFloat()
                            ),
                            cityName = "error",
                            visibility = 1.1111.toFloat(),
                            wind = Wind(
                                speed = 1.1111.toFloat()
                            ),
                            clouds = Clouds(
                                clouds = 100000
                            )

                        )
                        callback?.invoke(weather)
                    }
                    .collect {

                        callback?.invoke(
                            WeatherCommon.retrofitService.getWeatherRetrofit(
                                lat = lat,
                                lon = lon
                            )
                        )
                    }
            }
        } else {
            callback?.invoke(null)
        }
    }


    suspend fun getCoordinates(): List<Coordinates> {
        val city = cityRepository.getAllCities().filter {
            it.isSelected
        }
        if (city.isNotEmpty()) {
            return city.mapIndexed { _, cityEntity -> cityEntity.toCoordinates() }
        } else {
            return listOf()
        }
    }

    suspend fun buildList() {
        getSelectedCityForWeather { weather ->
            viewModelScope.launch {
                val city = getCoordinates()
                getSelectedCoins { coins ->
                    coins ?: return@getSelectedCoins
                    viewModelScope.launch {
                        val recyclerViewList = listOf(
                            DataModel.HeaderRVItemModel(
                                title = "Погода"
                            ),
                            DataModel.MainWeatherItemModel(
                                buttonText = "Выбрать город",
                                itemType = MainItemType.WEATHER,
                                weather = weather
                            ),
                            DataModel.HeaderRVItemModel(
                                title = "Город"
                            ),
                            DataModel.MainRVItemModel(
                                buttonText = "Выбрать город",
                                itemType = MainItemType.CITY,
                                coordinates = city
                            ),
                            DataModel.HeaderRVItemModel(
                                title = "Курс криптовалют"
                            ),
                            DataModel.MainCoinRVItemModel(
                                buttonText = "Выбрать криптовалюту",
                                itemType = MainItemType.COIN,
                                coins = coins
                            )
                        )
                        _list.emit(recyclerViewList)
                    }

                }
            }
        }

    }

}