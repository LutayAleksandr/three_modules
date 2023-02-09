package com.example.three_modules.app.presentation.ui.fragments.coin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.coin.viewholder.CoinRVViewHolder
import com.example.three_modules.databinding.ItemCoinRecyclerBinding
import kotlinx.android.synthetic.main.item_town_recycler.view.*

class CoinRVAdapter:
    ListAdapter<CoinRVItemModel, RecyclerView.ViewHolder>(CoinDiffCallback()) {

    var click: ((itemType: CoinRVItemModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CoinRVViewHolder(
            binding = ItemCoinRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CoinRVViewHolder) {
            val currentItem = currentList[position]
            holder.bind(item = currentItem)
            holder.click = click
        }
        holder.itemView.itrImageButton.setOnClickListener(View.OnClickListener() {
            fun onClick( view: View){

            }
        })
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)

    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    fun getSelectedCoin(): List<CoinRVItemModel>{
        val selectedCoinList: MutableList<CoinRVItemModel> = mutableListOf()
        for (coin in currentList) {
            if (coin.isSelected) {
                selectedCoinList.add(coin)
            }
        }
            return selectedCoinList
    }

    fun addCoins(coins: List<CoinRVItemModel>) {
        this.currentList.apply {
            clear()
            addAll(coins)
        }
        this.notifyDataSetChanged()
    }



}