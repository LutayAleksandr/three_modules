package com.example.three_modules.app.presentation.ui.fragments.main.viewholders.mainviewholder


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.threecoinadapter.ThreeCoinAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.google.android.material.button.MaterialButton
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.ui_view.ViewProvider
import kotlin.math.roundToInt


class MainRVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var click: ((itemType: MainItemType) -> Unit)? = null

    var rvAdapter = ThreeCoinAdapter()

    var clickReplace: ((itemType: MainItemType) -> Unit)? = null

    private fun bindMainMap(item: DataModel.MainRVItemModel) {
        val button = itemView.findViewById<MaterialButton>(R.id.imrButton)
        val buttonSettings = itemView.findViewById<View>(R.id.imrSettings)
        val cardMap = itemView.findViewById<MapView>(R.id.imrMapview)
        val pin = itemView.findViewById<View>(R.id.imrPin)
        button.text = item.buttonText
        val title: TextView = itemView.findViewById(R.id.imrHeader)
        title.text = item.title

        if (item.coordinates.isEmpty()) {
            button.setOnClickListener {
                click?.invoke(item.itemType)
            }
            buttonSettings.visibility = View.GONE
            cardMap.visibility = View.GONE
            pin.visibility = View.GONE
        } else {
            pin.visibility = View.GONE
            buttonSettings.setOnClickListener {
                click?.invoke(item.itemType)
            }
            button.visibility = View.GONE
            cardMap.map.mapObjects.addPlacemark(Point(item.coordinates.firstOrNull()?.latitude ?: 0.0,
                item.coordinates.firstOrNull()?.longitude ?: 0.0), ViewProvider(pin)
            )
            cardMap.map.isZoomGesturesEnabled = false
            cardMap.map.isScrollGesturesEnabled = false
            cardMap.map.move(
                CameraPosition(
                    Point(
                        item.coordinates.firstOrNull()?.latitude ?: 0.0,
                        item.coordinates.firstOrNull()?.longitude ?: 0.0
                    ), 8.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 0F), null
            )
        }

    }
    private fun bindMainWeather(item: DataModel.MainWeatherItemModel) {
        val button = itemView.findViewById<MaterialButton>(R.id.imwrButton)
        val buttonSettings = itemView.findViewById<View>(R.id.imwrSettings)
        val cardWeather = itemView.findViewById<View>(R.id.imwrWeather)
        val imwrCardButton = itemView.findViewById<View>(R.id.imwrCardButton)
        val title: TextView = itemView.findViewById(R.id.imwrHeader)
        title.text = item.title
        if (item.weather?.weather.isNullOrEmpty()) {
            button.text = item.buttonText
            button.setOnClickListener {
                click?.invoke(item.itemType)
            }
            buttonSettings.visibility = View.GONE
            cardWeather.visibility = View.GONE
            imwrCardButton.visibility = View.GONE
        } else {
            buttonSettings.setOnClickListener {
                click?.invoke(item.itemType)
            }
            button.visibility = View.GONE
            imwrCardButton.visibility = View.GONE
        }
        if (item.weather?.cityName == "error") {
            imwrCardButton.setOnClickListener {
                clickReplace?.invoke(item.itemType)
            }
            buttonSettings.setOnClickListener {
                click?.invoke(item.itemType)
            }
            button.visibility = View.GONE
            buttonSettings.visibility = View.VISIBLE
            cardWeather.visibility = View.GONE
            Toast.makeText(itemView.context, "При загрузке погоды произошла ошибка" , Toast.LENGTH_SHORT).show()
        }
        val icon = itemView.findViewById<ImageView>(R.id.imwrImage)
        item.weather ?: return

        Glide.with(itemView).load("https://openweathermap.org/img/wn/${item.weather.weather.firstOrNull()?.icon}@2x.png").into(icon)

        val description = itemView.findViewById<TextView>(R.id.imwrRain)
        description.text = item.weather.weather.firstOrNull()?.description

        val cityName = itemView.findViewById<TextView>(R.id.imwrCityName)
        cityName.text = item.weather.cityName

        val clouds = itemView.findViewById<TextView>(R.id.imwrCloudiness)
        clouds.text = item.weather.clouds.clouds.toString()

        val temp = itemView.findViewById<TextView>(R.id.imwrTemp)
        temp.text = "${(item.weather.main.temp - 273).roundToInt()}°C"

        val feelsLikeTemp = itemView.findViewById<TextView>(R.id.imwrFeelingTemp)
        feelsLikeTemp.text = "${(item.weather.main.feelsLikeTemp - 273).roundToInt()}°C"

        val pressure = itemView.findViewById<TextView>(R.id.imwrPressure)
        pressure.text = (item.weather.main.pressure * 0.750062).roundToInt().toString()

        val visibility = itemView.findViewById<TextView>(R.id.imwrVisibility)
        visibility.text = item.weather.visibility.roundToInt().toString()

        val wind = itemView.findViewById<TextView>(R.id.imwrWind)
        wind.text = item.weather.wind.speed.toString()

        val humidity = itemView.findViewById<TextView>(R.id.imwrHumidity)
        humidity.text = item.weather.main.humidity.roundToInt().toString()
    }

    private fun bindMainCoin(item: DataModel.MainCoinRVItemModel) {
        val button = itemView.findViewById<MaterialButton>(R.id.imcrButton)
        val buttonSettings = itemView.findViewById<View>(R.id.imcrSettings)
        val card = itemView.findViewById<View>(R.id.imcrRecyclerview)
        val cardButton = itemView.findViewById<View>(R.id.imcrCardButton)
        val title: TextView = itemView.findViewById(R.id.imcrHeader)
        title.text = item.title

        if (item.coins.isEmpty()) {
            button.text = item.buttonText
            button.setOnClickListener {
                click?.invoke(item.itemType)
            }
            buttonSettings.visibility = View.GONE
            cardButton.visibility = View.GONE
        } else {
            buttonSettings.setOnClickListener {
                click?.invoke(item.itemType)
            }
            button.visibility = View.GONE
            setupRecyclerViewThreeCoin(coins = item.coins)
            buttonSettings.visibility = View.VISIBLE
            cardButton.visibility = View.GONE
        }
        if (item.coins.firstOrNull()?.name == "error" || item.coins.firstOrNull()?.imageUrl == "error") {
            cardButton.setOnClickListener {
                clickReplace?.invoke(item.itemType)
            }
            cardButton.visibility = View.VISIBLE
            buttonSettings.setOnClickListener {
                click?.invoke(item.itemType)
            }
            button.visibility = View.GONE
            card.visibility = View.GONE
            Toast.makeText(itemView.context, "При загрузке криптовалюты произошла ошибка" , Toast.LENGTH_SHORT).show()
        }
    }

//    private fun bindHeader(data: DataModel.HeaderRVItemModel) {
//        val title: TextView = itemView.findViewById(R.id.ihmrTextView)
//        title.text = data.title
//    }

    fun bind(data: DataModel) {
        when(data){
//            is DataModel.HeaderRVItemModel -> bindHeader(data)
            is DataModel.MainRVItemModel -> bindMainMap(data)
            is DataModel.MainCoinRVItemModel -> bindMainCoin(data)
            is DataModel.MainWeatherItemModel -> bindMainWeather(data)
        }
    }

    private fun setupRecyclerViewThreeCoin(coins: List<CoinRVItemModel>) {
        val rv = itemView.findViewById<RecyclerView>(R.id.imcrRecyclerview)
        rvAdapter.submitList(coins)
        rv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = rvAdapter
    }
}