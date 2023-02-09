package com.example.three_modules.app.presentation.ui.fragments.coin.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel

class CoinDiffCallback:DiffUtil.ItemCallback<CoinRVItemModel>() {
    override fun areItemsTheSame(oldItem: CoinRVItemModel, newItem: CoinRVItemModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CoinRVItemModel, newItem: CoinRVItemModel): Boolean {
        return oldItem.isSelected == newItem.isSelected
    }

}