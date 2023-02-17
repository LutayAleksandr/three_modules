package com.example.three_modules.app.presentation.ui.fragments.main.adapters.threecoinadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.viewholders.threecoinviewholder.ThreeCoinRVViewHolder
import com.example.three_modules.databinding.ItemThreeCoinRecyclerviewBinding

class ThreeCoinAdapter(private val threeCoinRVItemModelList: List<CoinRVItemModel>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ThreeCoinRVViewHolder(
            binding = ItemThreeCoinRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ThreeCoinRVViewHolder) {
            val currentItem = threeCoinRVItemModelList[position]
            holder.bind(item = currentItem)
        }
    }

    override fun getItemCount(): Int {
        return threeCoinRVItemModelList.size
    }
}