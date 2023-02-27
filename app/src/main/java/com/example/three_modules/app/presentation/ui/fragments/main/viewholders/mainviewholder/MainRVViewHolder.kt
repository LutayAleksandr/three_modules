package com.example.three_modules.app.presentation.ui.fragments.main.viewholders.mainviewholder

import android.view.View
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.threecoinadapter.ThreeCoinAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.google.android.material.button.MaterialButton

class MainRVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var click: ((itemType: MainItemType) -> Unit)? = null

    private fun bindMain(item: DataModel.MainRVItemModel) {
//        val width = itemView.findViewById<CardView>(R.id.imrCardView).width
        val button = itemView.findViewById<MaterialButton>(R.id.imrButton)
        button.text = item.buttonText
        button.setOnClickListener {
            click?.invoke(item.itemType)
        }
    }

    private fun bindMainCoin(item: DataModel.MainCoinRVItemModel) {
        val button = itemView.findViewById<MaterialButton>(R.id.imcrButton)
        val buttonSettings = itemView.findViewById<View>(R.id.imcrSettings)

        if (item.coins.isEmpty()) {
            button.text = item.buttonText
            button.setOnClickListener {
                click?.invoke(item.itemType)
            }
            buttonSettings.visibility = View.GONE
        } else {
            buttonSettings.setOnClickListener {
                click?.invoke(item.itemType)
            }
            button.visibility = View.GONE
            setupRecyclerViewThreeCoin(coins = item.coins)
        }
    }

    private fun bindHeader(data: DataModel.HeaderRVItemModel) {
        val title: TextView = itemView.findViewById(R.id.ihmrTextView)
        title.text = data.title
    }

    fun bind(data: DataModel) {
        when(data){
            is DataModel.HeaderRVItemModel -> bindHeader(data)
            is DataModel.MainRVItemModel -> bindMain(data)
            is DataModel.MainCoinRVItemModel -> bindMainCoin(data)
        }
    }

    fun setupRecyclerViewThreeCoin(coins: List<CoinRVItemModel>) {
        val rv = itemView.findViewById<RecyclerView>(R.id.imcrRecyclerview)
        val threeCoinAdapter = ThreeCoinAdapter(
            coins
        )
        rv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = threeCoinAdapter
    }
}