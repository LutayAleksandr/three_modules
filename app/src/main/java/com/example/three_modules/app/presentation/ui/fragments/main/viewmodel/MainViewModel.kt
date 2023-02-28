package com.example.three_modules.app.presentation.ui.fragments.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.three_modules.app.data.CityRepository
import com.example.three_modules.app.data.CoinRepository
import com.example.three_modules.app.data.WeatherRepository
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.coin.models.toRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.main.retrofitweather.WeatherCommon
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherJsonApiModel
import com.example.three_modules.app.presentation.ui.retrofit.Common
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class MainViewModel @Inject constructor(
    private val coinRepository: CoinRepository,
    private val cityRepository: CityRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _coins = MutableSharedFlow<List<DataModel>>()
    val coins = _coins.asSharedFlow()
    private val _city = MutableSharedFlow<List<WeatherJsonApiModel>>()
    val cityOne = _city.asSharedFlow()

    suspend fun getSelectedCoins() = withContext(viewModelScope.coroutineContext) {
            val threeCoins = coinRepository.getAllCoins().filter {
                it.isSelected
            }
            if (threeCoins.isNotEmpty()) {
                val ids = threeCoins.joinToString(",") { it.id }
                _coins.emit(
                    Common.retrofitService.getThreeCoinsRetrofit(ids = ids)
                        .mapIndexed { index, coinJsonURLModel -> coinJsonURLModel.toRVItemModel(index = index) })
            } else {
                val coinIsEmpty = listOf<CoinRVItemModel>()
                _coins.emit(coinIsEmpty)
            }
    }

    suspend fun getSelectedCityForWeather() = withContext(viewModelScope.coroutineContext) {
        val city = weatherRepository.getAllCities().filter {
            it.isSelected
        }
        if (city.isNotEmpty()) {
            val lat = city.joinToString("") { it.latitude.toString() }
            val lon = city.joinToString("") { it.longitude.toString() }

            _city.emit(
                WeatherCommon.retrofitService.getWeatherRetrofit(lat = lat, lon = lon)
            )
        } else {
            val cityIsEmpty = listOf<WeatherJsonApiModel>()
            _city.emit(cityIsEmpty)
        }
    }

    fun buildList() {
        val coins = getSelectedCoins()
        val weather = getSelectedCityForWeather()
        val city = getSelectedCity()
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
                //TODO city = city
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