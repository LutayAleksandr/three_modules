package com.example.three_modules.app.presentation.ui.fragments.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.viewholders.MainRVViewHolder

class MainRVAdapter(private val mainRVItemModelList: List<MainRVItemModel>) :
    RecyclerView.Adapter<ViewHolder>() {

    var click: ((itemType: MainItemType) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_main_recycler, parent, false
            )
        return MainRVViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is MainRVViewHolder) {
            val currentItem = mainRVItemModelList[position]
            holder.bind(item = currentItem)
            holder.click = click
        }
    }

    override fun getItemCount(): Int {
        return mainRVItemModelList.size
    }
}