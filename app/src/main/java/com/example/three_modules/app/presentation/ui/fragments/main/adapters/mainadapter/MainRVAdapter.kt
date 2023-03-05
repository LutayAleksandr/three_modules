package com.example.three_modules.app.presentation.ui.fragments.main.adapters.mainadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.main.viewholders.mainviewholder.MainRVViewHolder

class MainRVAdapter(private val mainRVItemModelList: List<DataModel>) :
    RecyclerView.Adapter<ViewHolder>() {

    var click: ((itemType: MainItemType) -> Unit)? = null

    var clickReplace: ((itemType: MainItemType) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = when(viewType){
            VIEW_HEADER -> R.layout.item_header_main_recycler
            VIEW_MAIN_ITEMS -> R.layout.item_main_recycler
            VIEW_MAIN_COIN_ITEM -> R.layout.item_main_coin_recycler
            VIEW_MAIN_WEATHER_ITEM -> R.layout.item_main_weather_recycler
            else -> 0
        }

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                layout,
                parent,
                false
            )
        return MainRVViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is MainRVViewHolder) {
            val currentItem = mainRVItemModelList[position]
            holder.bind(data = currentItem)
            holder.click = click
            holder.clickReplace = clickReplace
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(mainRVItemModelList[position]){
            is DataModel.HeaderRVItemModel -> VIEW_HEADER
            is DataModel.MainRVItemModel -> VIEW_MAIN_ITEMS
            is DataModel.MainCoinRVItemModel -> VIEW_MAIN_COIN_ITEM
            is DataModel.MainWeatherItemModel -> VIEW_MAIN_WEATHER_ITEM
        }
    }

    override fun getItemCount(): Int {
        return mainRVItemModelList.size
    }

    companion object{
        const val VIEW_HEADER = 0
        const val VIEW_MAIN_ITEMS = 1
        const val VIEW_MAIN_COIN_ITEM = 2
        const val VIEW_MAIN_WEATHER_ITEM = 3
    }
}
