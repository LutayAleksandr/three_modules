package com.example.three_modules.app.presentation.ui.fragments.weather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.weather.viewholders.WeatherRVViewHolder
import com.example.three_modules.databinding.ItemWeatherRecyclerBinding

class WeatherRVAdapter :
    ListAdapter<WeatherRVItemModel, RecyclerView.ViewHolder>(WeatherDiffCallback()) {

    var click: ((itemType: WeatherRVItemModel, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WeatherRVViewHolder(
            binding = ItemWeatherRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WeatherRVViewHolder) {
            val currentItem = currentList[position]
            holder.bind(item = currentItem)
            holder.click = click
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)

    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}