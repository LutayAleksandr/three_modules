package com.example.three_modules.app.presentation.ui.fragments.main.adapters

import android.content.Intent
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.SettingFragment
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.main.viewholders.MainRVViewHolder
import java.util.Collections.addAll

class MainRVAdapter(private val mainRVItemModelList: List<DataModel>) :
    RecyclerView.Adapter<ViewHolder>() {

    var click: ((itemType: MainItemType) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = when(viewType){
            VIEW_HEADER -> R.layout.item_header_main_recycler
            VIEW_MAIN_ITEMS -> R.layout.item_main_recycler
            else -> 0
        }

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                layout, parent, false
            )
        return MainRVViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is MainRVViewHolder) {
            val currentItem = mainRVItemModelList[position]
            holder.bind(data = currentItem)
            holder.click = click
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(mainRVItemModelList[position]){
            is DataModel.HeaderRVItemModel -> VIEW_HEADER
            is DataModel.MainRVItemModel -> VIEW_MAIN_ITEMS
        }
    }

    override fun getItemCount(): Int {
        return mainRVItemModelList.size
    }

    companion object{
        const val VIEW_HEADER = 0
        const val VIEW_MAIN_ITEMS = 1
    }
}
