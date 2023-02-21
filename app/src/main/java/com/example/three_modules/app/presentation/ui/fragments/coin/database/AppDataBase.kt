package com.example.three_modules.app.presentation.ui.fragments.coin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel

@Database(
    entities = [CoinRVItemModel::class], version = 1
)

abstract class AppDataBase: RoomDatabase() {

    abstract fun coinsDao(): CoinsDao

    companion object{
        fun getDB(context: Context):AppDataBase{
            return Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "coin.db"
            ).build()
        }
    }
}