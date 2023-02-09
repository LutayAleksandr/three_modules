package com.example.three_modules.app.presentation.ui.fragments.weather.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherRVItemModel

class WeatherDiffCallback: DiffUtil.ItemCallback<WeatherRVItemModel>() {

    override fun areItemsTheSame(oldItem: WeatherRVItemModel, newItem: WeatherRVItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WeatherRVItemModel, newItem: WeatherRVItemModel): Boolean {
        return oldItem.isSelected == newItem.isSelected
    }


}