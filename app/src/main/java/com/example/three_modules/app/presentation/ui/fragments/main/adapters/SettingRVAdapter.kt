package com.example.three_modules.app.presentation.ui.fragments.main.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.main.models.SettingRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.viewholders.SettingRVViewHolder
import com.example.three_modules.databinding.ItemSettingsRecyclerBinding

class SettingRVAdapter(private val settingRVItemModelList: List<SettingRVItemModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SettingRVViewHolder(
            binding = ItemSettingsRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SettingRVViewHolder) {
            val currentItem = settingRVItemModelList[position]
            holder.bind(item = currentItem)
        }
    }

    override fun getItemCount(): Int {
        return settingRVItemModelList.size
    }
}