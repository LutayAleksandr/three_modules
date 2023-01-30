package com.example.three_modules.app.presentation.ui.fragments.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.main.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.viewholders.CoinRVViewHolder
import com.example.three_modules.databinding.ItemCoinRecyclerBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_recycler.view.*

class CoinRVAdapter(
    private val coinRVItemModelList: MutableList<CoinRVItemModel>
) :
RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

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
        val item = coinRVItemModelList[position]
        val imageCoin = holder.itemView.icrImageView

        Picasso.get()
            .load(coinRVItemModelList[position].imageUrl)
            .into(imageCoin)

        holder.itemView.icrTextView.text = item.name

        if (holder is CoinRVViewHolder) {
            val currentItem = coinRVItemModelList[position]
            holder.bind(item = currentItem)
            holder.click = click
        }
    }

    override fun getItemCount(): Int {
        return coinRVItemModelList.size
    }

}