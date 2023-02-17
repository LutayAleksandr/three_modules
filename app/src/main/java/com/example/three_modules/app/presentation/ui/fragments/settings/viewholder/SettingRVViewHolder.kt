package com.example.three_modules.app.presentation.ui.fragments.settings.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.settings.model.SettingRVItemModel
import com.example.three_modules.databinding.ItemSettingsRecyclerBinding


class SettingRVViewHolder(val binding: ItemSettingsRecyclerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SettingRVItemModel) {
        binding.apply {
            isrCardView.setCardBackgroundColor(item.color)
            isrTextView.text = item.textModules
        }
    }
}
