package com.example.three_modules.app.presentation.ui.fragments.main.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityRVItemModel
import com.example.three_modules.databinding.ItemTownRecyclerBinding

class CityRVViewHolder(val binding: ItemTownRecyclerBinding) :
    RecyclerView.ViewHolder(binding.root)  {

    var click: ((itemType: CityRVItemModel) -> Unit)? = null

    fun bind(item: CityRVItemModel) {
        binding.apply {
            itrCardView.setCardBackgroundColor(item.color)
            itrTextView2.text = item.cityName
            itrTextView1.text = item.countryName
            itrImageButton.setOnClickListener{
                click?.invoke(item)
            }
        }
    }
}