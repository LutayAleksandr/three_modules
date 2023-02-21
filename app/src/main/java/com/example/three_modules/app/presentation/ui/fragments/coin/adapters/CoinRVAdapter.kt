package com.example.three_modules.app.presentation.ui.fragments.coin.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.coin.viewholder.CoinRVViewHolder
import com.example.three_modules.databinding.ItemCoinRecyclerBinding

class CoinRVAdapter:
    ListAdapter<CoinRVItemModel, RecyclerView.ViewHolder>(CoinDiffCallback()) {

    var click: ((itemType: CoinRVItemModel, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CoinRVViewHolder(
            binding = ItemCoinRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CoinRVViewHolder) {
            val currentItem = currentList[position]
            holder.bind(item = currentItem)
            holder.click = click

            getItem(position)?.let { holder.bind(it) }
        }

    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}