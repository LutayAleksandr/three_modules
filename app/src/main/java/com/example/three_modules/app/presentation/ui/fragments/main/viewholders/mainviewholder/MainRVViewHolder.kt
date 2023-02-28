package com.example.three_modules.app.presentation.ui.fragments.main.viewholders.mainviewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.threecoinadapter.ThreeCoinAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherJsonApiModel
import com.google.android.material.button.MaterialButton

class MainRVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var click: ((itemType: MainItemType) -> Unit)? = null

    private fun bindMain(item: DataModel.MainRVItemModel) {
//      val width = itemView.findViewById<CardView>(R.id.imrCardView).width
        val button = itemView.findViewById<MaterialButton>(R.id.imrButton)
        button.text = item.buttonText
        button.setOnClickListener {
            click?.invoke(item.itemType)
        }
    }
    private fun bindMainWeather(item: DataModel.MainWeatherItemModel) {
        val button = itemView.findViewById<MaterialButton>(R.id.imwrButton)
        val buttonSettings = itemView.findViewById<View>(R.id.imwrSettings)
        val cardWeather = itemView.findViewById<View>(R.id.imwrCardView)
        if (item.weather.weather.isEmpty()) {
            button.text = item.buttonText
            button.setOnClickListener {
                click?.invoke(item.itemType)
            }
            buttonSettings.visibility = View.GONE
            cardWeather.visibility = View.GONE
        } else {
            buttonSettings.setOnClickListener {
                click?.invoke(item.itemType)
            }
            button.visibility = View.GONE
        }
        val icon = itemView.findViewById<ImageView>(R.id.imwrImage)
        Glide.with(itemView).load("http://openweathermap.org/img/wn/${item.weather.weather[0].icon}.png").into(icon)
        val description = itemView.findViewById<TextView>(R.id.imwrRain)
        description.text = item.weather.weather[0].description
        val cityName = itemView.findViewById<TextView>(R.id.imwrCityName)
        cityName.text = item.weather.cityName
        val clouds = itemView.findViewById<TextView>(R.id.imwrCloudiness)
        clouds.text = item.weather.clouds[0].clouds.toString()
        val temp = itemView.findViewById<TextView>(R.id.imwrTemp)
        temp.text = "${item.weather.main[0].temp}°C"
        val feelsLikeTemp = itemView.findViewById<TextView>(R.id.imwrFeelingTemp)
        feelsLikeTemp.text = "${item.weather.main[0].feelsLikeTemp}°C"
        val pressure = itemView.findViewById<TextView>(R.id.imwrPressure)
        pressure.text = item.weather.main[0].pressure.toString()
        val visibility = itemView.findViewById<TextView>(R.id.imwrVisibility)
        visibility.text = item.weather.visibility.toString()
        val wind = itemView.findViewById<TextView>(R.id.imwrWind)
        wind.text = item.weather.wind[0].speed.toString()
        val humidity = itemView.findViewById<TextView>(R.id.imwrHumidity)
        humidity.text = item.weather.main[0].humidity.toString()

    }

    private fun bindMainCoin(item: DataModel.MainCoinRVItemModel) {
        val button = itemView.findViewById<MaterialButton>(R.id.imcrButton)
        val buttonSettings = itemView.findViewById<View>(R.id.imcrSettings)


        if (item.coins.isEmpty()) {
            button.text = item.buttonText
            button.setOnClickListener {
                click?.invoke(item.itemType)
            }
            buttonSettings.visibility = View.GONE
        } else {
            buttonSettings.setOnClickListener {
                click?.invoke(item.itemType)
            }
            button.visibility = View.GONE
            setupRecyclerViewThreeCoin(coins = item.coins)
        }
    }

    private fun bindHeader(data: DataModel.HeaderRVItemModel) {
        val title: TextView = itemView.findViewById(R.id.ihmrTextView)
        title.text = data.title
    }

    fun bind(data: DataModel) {
        when(data){
            is DataModel.HeaderRVItemModel -> bindHeader(data)
            is DataModel.MainRVItemModel -> bindMain(data)
            is DataModel.MainCoinRVItemModel -> bindMainCoin(data)
            is DataModel.MainWeatherItemModel -> bindMainWeather(data)
        }
    }

    private fun setupRecyclerViewThreeCoin(coins: List<CoinRVItemModel>) {
        val rv = itemView.findViewById<RecyclerView>(R.id.imcrRecyclerview)
        val threeCoinAdapter = ThreeCoinAdapter(
            coins
        )
        rv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = threeCoinAdapter
    }
}