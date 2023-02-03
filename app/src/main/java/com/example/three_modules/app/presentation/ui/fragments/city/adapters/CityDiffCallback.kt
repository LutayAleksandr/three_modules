package com.example.three_modules.app.presentation.ui.fragments.city.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityRVItemModel

class CityDiffCallback: DiffUtil.ItemCallback<CityRVItemModel>() {

    override fun areItemsTheSame(oldItem: CityRVItemModel, newItem: CityRVItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CityRVItemModel, newItem: CityRVItemModel): Boolean {
        return oldItem.isSelected == newItem.isSelected
    }


}