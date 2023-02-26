package com.example.three_modules.app.presentation.ui.fragments.city.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.three_modules.app.presentation.ui.fragments.city.models.CityEntity
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinEntity

@Dao
abstract class CityDao {

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     abstract suspend fun insert(city: MutableList<CityEntity>)

     @Query("UPDATE cities_table SET isSelected = :isSelected WHERE id =:id")
     abstract suspend fun updateSelection(isSelected: Boolean, id: Int)

     @Query("SELECT * FROM cities_table")
     abstract suspend fun getAllCities(): MutableList<CityEntity>
}