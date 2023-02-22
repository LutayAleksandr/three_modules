package com.example.three_modules.app.presentation.ui.fragments.coin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinEntity

@Database(
    entities = [CoinEntity::class], version = 1
)
abstract class CoinDatabase: RoomDatabase() {

    abstract fun coinsDao(): CoinsDao
}