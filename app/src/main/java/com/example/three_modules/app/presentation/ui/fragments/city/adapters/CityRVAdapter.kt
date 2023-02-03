package com.example.three_modules.app.presentation.ui.fragments.city.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.city.viewholders.CityRVViewHolder
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityRVItemModel
import com.example.three_modules.databinding.ItemTownRecyclerBinding
import kotlinx.android.synthetic.main.item_town_recycler.view.*

class CityRVAdapter :
    ListAdapter<CityRVItemModel, RecyclerView.ViewHolder>(CityDiffCallback()) {

    var click: ((itemType: CityRVItemModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CityRVViewHolder(
            binding = ItemTownRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = currentList[position]

        holder.itemView.itrTextView1.text = item.countryName
        holder.itemView.itrTextView2.text = item.cityName

        if (holder is CityRVViewHolder) {
            val currentItem = currentList[position]
            holder.bind(item = currentItem)
            holder.click = click
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}