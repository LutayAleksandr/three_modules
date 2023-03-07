package com.example.three_modules.app.presentation.ui.fragments.settings.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.three_modules.R

data class SettingRVItemModel(
    val id: Int ,
    val textModules: String,
    val color: Int,
    val orderPosition: Int
)

@Entity(tableName = "modules_table")
data class SettingEntity(
    @PrimaryKey
    val id: Int ,
    val textModules: String,
    val orderPosition: Int
)

fun SettingEntity.toItem(index: Int):SettingRVItemModel {
    return SettingRVItemModel(
        id = this.id,
        textModules = this.textModules,
        color = if (index % 2 == 0) R.color.blue  else R.color.lightBlue,
        orderPosition = this.orderPosition
    )
}

fun SettingRVItemModel.toEntity(): SettingEntity {
    return SettingEntity(
        id = this.id,
        textModules = this.textModules,
        orderPosition = this.orderPosition
    )
}

