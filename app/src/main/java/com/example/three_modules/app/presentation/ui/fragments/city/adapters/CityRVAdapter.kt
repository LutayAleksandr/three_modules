package com.example.three_modules.app.presentation.ui.fragments.city.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.city.models.CityRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.city.viewholders.CityRVViewHolder
import com.example.three_modules.databinding.ItemTownRecyclerBinding

class CityRVAdapter :
    ListAdapter<CityRVItemModel, RecyclerView.ViewHolder>(CityDiffCallback()) {

    var click: ((itemType: CityRVItemModel, position: Int) -> Unit)? = null

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
        if (holder is CityRVViewHolder) {
            val currentItem = currentList[position]
            holder.bind(item = currentItem)
            holder.click = click

            getItem(position)?.let { holder.bind(it) }
        }
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
}