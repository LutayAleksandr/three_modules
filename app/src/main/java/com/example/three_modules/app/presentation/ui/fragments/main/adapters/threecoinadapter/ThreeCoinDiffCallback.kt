package com.example.three_modules.app.presentation.ui.fragments.main.adapters.threecoinadapter

import androidx.recyclerview.widget.DiffUtil
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel

class ThreeCoinDiffCallback: DiffUtil.ItemCallback<CoinRVItemModel>() {

    override fun areItemsTheSame(oldItem: CoinRVItemModel, newItem: CoinRVItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CoinRVItemModel, newItem: CoinRVItemModel): Boolean {
        return oldItem.id == newItem.id
    }
}