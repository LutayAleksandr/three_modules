package com.example.three_modules.app.presentation.ui.fragments.coin.database

import androidx.room.*
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinEntity

@Dao
abstract class CoinsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(coins: MutableList<CoinEntity>)

    @Query("SELECT * FROM coins_table")
    abstract suspend fun getAllCoins(): MutableList<CoinEntity>

    @Update
    abstract suspend fun coinUpdate(coins: MutableList<CoinEntity>)

    @Query("SELECT * FROM coins_table WHERE isSelected =:isSelected")
    abstract suspend fun getCoinsBySelection(isSelected: Boolean): List<CoinEntity>

    @Query("DELETE FROM coins_table")
    abstract suspend fun deleteAll()

    @Query("UPDATE coins_table SET isSelected = :isSelected WHERE id =:id")
    abstract suspend fun updateSelection(isSelected: Boolean, id: String)
}