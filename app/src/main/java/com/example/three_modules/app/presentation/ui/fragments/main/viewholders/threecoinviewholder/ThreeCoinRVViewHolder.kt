package com.example.three_modules.app.presentation.ui.fragments.main.viewholders.threecoinviewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.databinding.ItemThreeCoinRecyclerviewBinding

class ThreeCoinRVViewHolder(val binding: ItemThreeCoinRecyclerviewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CoinRVItemModel) {
        binding.apply {
            itcrNameCoin.text = item.name
            itcrPriceCoin.text = item.current_price.toString()
            itcrPriceChange.text = item.price_change_24h.toString()
            Glide.with(itemView).load(item.imageUrl).placeholder(R.drawable.ic_coin_placeholder)
                .into(itcrImageCoin)
        }
    }
}