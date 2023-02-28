package com.example.three_modules.app.presentation.ui.fragments.weather.viewholders

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherRVItemModel
import com.example.three_modules.databinding.ItemWeatherRecyclerBinding

class WeatherRVViewHolder(val binding: ItemWeatherRecyclerBinding) :
    RecyclerView.ViewHolder(binding.root)  {

    var click: ((itemType: WeatherRVItemModel, position: Int) -> Unit)? = null

    fun bind(item: WeatherRVItemModel) {
        binding.apply {
            iwrTextView2.text = item.cityName
            iwrTextView1.text = item.countryName
            if (item.isSelected ) {
                iwrImageButton.setImageResource(R.drawable.ic_check)
            } else {
                iwrImageButton.setImageResource(R.drawable.ic_add)
            }
            iwrImageButton.setOnClickListener {
                click?.invoke(item, absoluteAdapterPosition)
            }

            if (item.id % 2 == 0) {
                iwrCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.lightBlue))
            }else {
                iwrCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.blue))
            }
        }
    }
}