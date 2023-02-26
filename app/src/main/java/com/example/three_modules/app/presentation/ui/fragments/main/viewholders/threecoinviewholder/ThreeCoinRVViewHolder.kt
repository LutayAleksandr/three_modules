package com.example.three_modules.app.presentation.ui.fragments.main.viewholders.threecoinviewholder

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.databinding.ItemThreeCoinRecyclerviewBinding

class ThreeCoinRVViewHolder(val binding: ItemThreeCoinRecyclerviewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    fun bind(item: CoinRVItemModel) {
        binding.apply {
            itcrNameCoin.text = item.name
            itcrPriceCoin.text = "${item.currentPrice} $"
            itcrPriceChange.text = item.priceChange24h.toString()
            if (item.priceChange24h > 0){
                itcrPriceChange.setTextColor(android.graphics.Color.GREEN)
                itcrImageChange.setImageResource(R.drawable.ic_up)
                itcrImageChange.setColorFilter(android.graphics.Color.GREEN)
            }
            if(item.priceChange24h < 0){
                itcrPriceChange.setTextColor(android.graphics.Color.RED)
                itcrImageChange.setImageResource(R.drawable.ic_down)
                itcrImageChange.setColorFilter(android.graphics.Color.RED)
            }
            Glide.with(itemView).load(item.imageUrl).placeholder(R.drawable.ic_coin_placeholder)
                .into(itcrImageCoin)
        }
    }
}