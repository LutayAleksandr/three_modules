package com.example.three_modules.app.presentation.ui.fragments.main.viewholders

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.CoinRVItemModel
import com.example.three_modules.databinding.ItemCoinRecyclerBinding
import kotlinx.android.synthetic.main.item_coin_recycler.view.*


class CoinRVViewHolder(val binding: ItemCoinRecyclerBinding) :
    RecyclerView.ViewHolder(binding.root)  {

    var click: ((itemType: CoinRVItemModel) -> Unit)? = null

    fun bind(item: CoinRVItemModel) {
        var imageURL: ImageView
        var coinName: TextView
        binding.apply {
            //icrCardView.setCardBackgroundColor(item.color)
            coinName= itemView.icrTextView
            imageURL = itemView.icrImageView
            icrImageButton.setOnClickListener{
                click?.invoke(item)
            }
        }
    }
}