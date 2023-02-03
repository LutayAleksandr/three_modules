package com.example.three_modules.app.presentation.ui.fragments.city.viewholders

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityJsonModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityRVItemModel
import com.example.three_modules.databinding.ItemTownRecyclerBinding

class CityRVViewHolder(val binding: ItemTownRecyclerBinding) :
    RecyclerView.ViewHolder(binding.root)  {

    var click: ((itemType: CityRVItemModel) -> Unit)? = null

    fun bind(item: CityRVItemModel) {
        binding.apply {
            itrCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, item.color))
            itrTextView2.text = item.cityName
            itrTextView1.text = item.countryName
            if (item.isSelected) {
                itrImageButton.setBackgroundResource(R.drawable.ic_check)
            } else {
                itrImageButton.setBackgroundResource(R.drawable.ic_add)
            }
            itrImageButton.setOnClickListener {
                item.isSelected = true
                click?.invoke(item)
            }
        }
    }
}