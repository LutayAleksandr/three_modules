package com.example.three_modules.app.presentation.ui.fragments.coin.viewholder

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.widget.RecyclerView

//class CoinLookup(private val coinRecyclerView: RecyclerView)
//    : ItemDetailsLookup<Long>() {
//    override fun getItemDetails(event: MotionEvent)
//            : ItemDetails<Long>? {
//        val view = coinRecyclerView.findChildViewUnder(event.x, event.y)
//        if(view != null) {
//            return (coinRecyclerView.getChildViewHolder(view) as CoinRVViewHolder)
//                .getItemDetails()
//        }
//        return null
//    }
//}
//
//class ItemIdKeyProvider(private val recyclerView: RecyclerView)
//    : ItemKeyProvider<Long>(SCOPE_MAPPED) {
//
//    override fun getKey(position: Int): Long {
//        return recyclerView.adapter?.getItemId(position)
//            ?: throw IllegalStateException("RecyclerView adapter is not set!")
//    }
//
//    override fun getPosition(key: Long): Int {
//        val viewHolder = recyclerView.findViewHolderForItemId(key)
//        return viewHolder?.layoutPosition ?: RecyclerView.NO_POSITION
//    }
//}

