package com.example.three_modules.app.presentation.ui.fragments.main.adapters.mainadapter

import androidx.recyclerview.widget.DiffUtil
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel

class MainDiffCallback: DiffUtil.ItemCallback<DataModel.MainCoinRVItemModel>() {

    override fun areItemsTheSame(oldItem: DataModel.MainCoinRVItemModel, newItem: DataModel.MainCoinRVItemModel): Boolean {
        return oldItem.coins == newItem.coins
    }

    override fun areContentsTheSame(oldItem: DataModel.MainCoinRVItemModel, newItem: DataModel.MainCoinRVItemModel): Boolean {
        return oldItem.coins == newItem.coins
    }
}