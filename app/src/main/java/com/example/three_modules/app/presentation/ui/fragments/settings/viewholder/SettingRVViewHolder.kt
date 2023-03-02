package com.example.three_modules.app.presentation.ui.fragments.settings.viewholder


import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.settings.model.SettingRVItemModel
import com.example.three_modules.databinding.ItemSettingsRecyclerBinding


class SettingRVViewHolder(val binding: ItemSettingsRecyclerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SettingRVItemModel) {
        binding.apply {
            isrTextView.text = item.textModules
            isrCardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, item.color))
        }
    }
}
