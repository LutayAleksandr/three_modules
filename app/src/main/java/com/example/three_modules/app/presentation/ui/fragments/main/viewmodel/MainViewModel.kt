package com.example.three_modules.app.presentation.ui.fragments.main.viewmodel

import android.os.Handler
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
import com.example.three_modules.app.presentation.ui.fragments.main.retrofitweather.WeatherCommon
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherJsonApiModel
import com.example.three_modules.app.presentation.ui.retrofit.Common
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class MainViewModel @Inject constructor(
    private val coinRepository: CoinRepository,
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository,
//    private val apiHelper: CoinApiHelper
) : ViewModel() {

    private val _list = MutableSharedFlow<List<DataModel>>()
    val list = _list.asSharedFlow()

    private var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 5000



    suspend fun getSelectedCoins():List<CoinRVItemModel> {
        val threeCoins = coinRepository.getAllCoins().filter {
            it.isSelected
        }
        if (threeCoins.isNotEmpty()) {
            val ids = threeCoins.joinToString(",") { it.id }
            val coin = Common.retrofitService.getThreeCoinsRetrofit(ids = ids)
                .mapIndexed { index, coinJsonURLModel -> coinJsonURLModel.toRVItemModel(index = index) }
            return (coin)

        } else {
            val coinIsEmpty = listOf<CoinRVItemModel>()
            return (coinIsEmpty)
        }
//        viewModelScope.launch {
//            apiHelper.getThreeCoinsRetrofit()
//                .flowOn(Dispatchers.IO)
//                .catch { e ->
//
//                }
//                .collect{
//                    val ids = threeCoins.joinToString(",") { it.id }
//                    Common.retrofitService.getThreeCoinsRetrofit(ids = ids)
//                        .mapIndexed { index, coinJsonURLModel -> coinJsonURLModel.toRVItemModel(index = index) }
//                }
//        }
    }

    suspend fun getSelectedCityForWeather(callback: ((WeatherJsonApiModel?) -> Unit)? = null) {
        val city = weatherRepository.getAllCities().filter {
            it.isSelected
        }
        if (city.isNotEmpty()) {
            val lat = city.joinToString("") { it.latitude.toString() }
            val lon = city.joinToString("") { it.longitude.toString() }
            callback?.invoke(WeatherCommon.retrofitService.getWeatherRetrofit(lat = lat, lon = lon))
        } else {
            callback?.invoke(null)
        }
    }


    suspend fun getCoordinates(): List<Coordinates>{
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
                val coins = getSelectedCoins()
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