package com.example.three_modules.app.presentation.ui.fragments.main.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.main.models.CoinRVItemModel
import com.example.three_modules.databinding.ItemCoinRecyclerBinding


class CoinRVViewHolder(val binding: ItemCoinRecyclerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var click: ((itemType: CoinRVItemModel) -> Unit)? = null

    fun bind(item: CoinRVItemModel) {
        binding.apply {
            icrImageButton.setOnClickListener {
                click?.invoke(item)
            }
            icrTextView.text = item.name
            Glide.with(itemView).load(item.imageUrl).placeholder(R.drawable.ic_coin_placeholder)
                .into(icrImageView)
        }
    }
}