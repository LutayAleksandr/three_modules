package com.example.three_modules.app.presentation.ui.fragments.main.viewholders.mainviewholder

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.threecoinadapter.ThreeCoinAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.databinding.ItemMainCoinRecyclerBinding
import com.google.android.material.button.MaterialButton

class MainRVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var click: ((itemType: MainItemType) -> Unit)? = null
    private var _binding: ItemMainCoinRecyclerBinding? = null
    private val binding get() = _binding!!

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
        button.text = item.buttonText
        button.setOnClickListener {
            click?.invoke(item.itemType)
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

        fun setupRecyclerViewThreeCoin() {
            binding.imcrButton.visibility = View.GONE

            val recyclerViewThreeCoinList = listOf(
                CoinRVItemModel(
                    id = 1,
                    name = "Bitcoin",
                    imageUrl = "",
                    current_price = "25435.23".toFloat(),
                    price_change_24h = "100.233".toFloat(),
                    color = Color.BLUE
                ),
                CoinRVItemModel(
                    id = 1,
                    name = "Bitcoin",
                    imageUrl = "",
                    current_price = "25435.23".toFloat(),
                    price_change_24h = "100.233".toFloat(),
                    color = Color.BLUE
                ),
                CoinRVItemModel(
                    id = 1,
                    name = "Bitcoin",
                    imageUrl = "",
                    current_price = "25435.23".toFloat(),
                    price_change_24h = "100.233".toFloat(),
                    color = Color.BLUE
                ),

            )
            val threeCoinAdapter = ThreeCoinAdapter(
                recyclerViewThreeCoinList
            )
            binding.imcrRecyclerview.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            binding.imcrRecyclerview.adapter = threeCoinAdapter
        }


}