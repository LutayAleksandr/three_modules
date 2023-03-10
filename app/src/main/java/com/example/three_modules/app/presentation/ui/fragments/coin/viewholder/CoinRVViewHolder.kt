package com.example.three_modules.app.presentation.ui.fragments.coin.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.databinding.ItemCoinRecyclerBinding


class CoinRVViewHolder(val binding: ItemCoinRecyclerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var click: ((itemType: CoinRVItemModel, position: Int) -> Unit)? = null

    fun bind(item: CoinRVItemModel) {
        binding.apply {
            icrTextView.text = item.name
            Glide.with(itemView).load(item.imageUrl).placeholder(R.drawable.ic_coin_placeholder)
                .into(icrImageView)
            if (item.isSelected) {
                icrImageButton.setImageResource(R.drawable.ic_check)
            } else {
                icrImageButton.setImageResource(R.drawable.ic_add)
            }
            itemView.setOnClickListener {
                click?.invoke(item, absoluteAdapterPosition)
            }

            if (item.marketCapRank % 2 == 0) {
                icrCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.lightBlue))
            }else {
                icrCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.blue))
            }
        }
    }
}