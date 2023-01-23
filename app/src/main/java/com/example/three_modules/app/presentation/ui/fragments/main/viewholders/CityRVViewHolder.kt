package com.example.three_modules.app.presentation.ui.fragments.main.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityItemType
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.databinding.ItemTownRecyclerBinding
import com.google.android.material.button.MaterialButton

class CityRVViewHolder(val binding: ItemTownRecyclerBinding) :
    RecyclerView.ViewHolder(binding.root)  {

    var click: ((itemType: CityItemType) -> Unit)? = null

    fun bind(item: CityRVItemModel) {
        binding.apply {
            itrCardView.setCardBackgroundColor(item.color)
            itrTextView2.text = item.cityName
            itrTextView1.text = item.countryName
            itrImageButton.setOnClickListener{
                click?.invoke(item.itemType)
            }
        }
    }
}