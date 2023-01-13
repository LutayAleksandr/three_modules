package com.example.three_modules.app.presentation.ui.fragments.main.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainRVItemModel
import com.google.android.material.button.MaterialButton

class MainRVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var click: ((itemType: MainItemType) -> Unit)? = null

    fun bind(item: MainRVItemModel) {
        val button = itemView.findViewById<MaterialButton>(R.id.imrButton)
        button.text = item.buttonText
        button.setOnClickListener {
            click?.invoke(item.itemType)
        }
    }

}