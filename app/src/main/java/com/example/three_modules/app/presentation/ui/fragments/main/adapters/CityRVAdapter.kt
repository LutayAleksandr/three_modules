package com.example.three_modules.app.presentation.ui.fragments.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityJsonModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.viewholders.CityRVViewHolder
import com.example.three_modules.databinding.ItemTownRecyclerBinding
import kotlinx.android.synthetic.main.item_town_recycler.view.*

class CityRVAdapter(private val cityRVItemModelList: List<CityJsonModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var click: ((itemType: CityJsonModel) -> Unit)? = null

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

        val item = cityRVItemModelList[position]

        holder.itemView.itrTextView1.text = item.countryName
        holder.itemView.itrTextView2.text = item.cityName

        if (holder is CityRVViewHolder) {
            val currentItem = cityRVItemModelList[position]
            holder.bind(item = currentItem)
            holder.click = click
        }
    }

    override fun getItemCount(): Int {
        return cityRVItemModelList.size
    }
}