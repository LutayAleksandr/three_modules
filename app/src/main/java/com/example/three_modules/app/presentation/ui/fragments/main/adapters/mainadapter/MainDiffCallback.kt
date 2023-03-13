package com.example.three_modules.app.presentation.ui.fragments.main.adapters.mainadapter

import androidx.recyclerview.widget.DiffUtil
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel

class MainDiffCallback:DiffUtil.ItemCallback<DataModel>() {

    override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
        if (oldItem is DataModel.MainCoinRVItemModel && newItem is DataModel.MainCoinRVItemModel) {
            return oldItem.coins == newItem.coins
        }
        if (oldItem is DataModel.MainRVItemModel && newItem is DataModel.MainRVItemModel) {
            return oldItem.coordinates == newItem.coordinates
        }
        if (oldItem is DataModel.MainWeatherItemModel && newItem is DataModel.MainWeatherItemModel) {
            return oldItem.weather == newItem.weather
        }
        return oldItem.id == newItem.id
    }

//    override fun getChangePayload(oldItem: DataModel, newItem: DataModel): Any? {
//        return super.getChangePayload(oldItem, newItem)
//    }
}