package com.example.three_modules.app.presentation.ui.fragments.coin.database

import androidx.room.*
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel

@Dao
interface CoinsDao {

    @Insert
    fun insert(coins: MutableList<CoinRVItemModel>)

    @Query("SELECT * FROM CoinItems")
    fun getAllCoins(): MutableList<CoinRVItemModel>

    @Update
    suspend fun coinUpdate(coins: MutableList<CoinRVItemModel>)

    @Query("DELETE FROM CoinItems")
    suspend fun deleteAllCoins()
}