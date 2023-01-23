package com.example.three_modules.app.presentation.ui.fragments.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityItemType
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.main.viewholders.CityRVViewHolder
import com.example.three_modules.app.presentation.ui.fragments.main.viewholders.SettingRVViewHolder
import com.example.three_modules.databinding.ItemSettingsRecyclerBinding
import com.example.three_modules.databinding.ItemTownRecyclerBinding

class CityRVAdapter(private val cityRVItemModelList: List<CityRVItemModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var click: ((itemType: CityItemType) -> Unit)? = null

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
            val currentItem = cityRVItemModelList[position]
            holder.bind(item = currentItem)
            holder.click = click
        }
    }

    override fun getItemCount(): Int {
        return cityRVItemModelList.size
    }
}