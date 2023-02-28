package com.example.three_modules.app.presentation.ui.fragments.main.viewholders.weatherviewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherJsonApiModel
import com.example.three_modules.databinding.ItemMainWeatherRecyclerBinding

class WeatherViewHolder(val binding: ItemMainWeatherRecyclerBinding) : ViewHolder(binding.root) {
    fun bind(item: WeatherJsonApiModel) {
        binding.apply {
            Glide.with(itemView).load("http://openweathermap.org/img/wn/${item.weather[1]}.png").into(imwrImage)
            imwrRain.text = item.weather[0].description
            imwrCityName.text = item.cityName
            imwrCloudiness.text = item.clouds[0].toString()
            imwrTemp.text = "${item.main[0]}°C"
            imwrFeelingTemp.text = "${item.main[1]}°C"
            imwrPressure.text = item.main[2].toString()
            imwrVisibility.text = item.visibility.toString()
            imwrWind.text = item.wind[0].toString()
            imwrHumidity.text = item.main[3].toString()
        }
    }
}