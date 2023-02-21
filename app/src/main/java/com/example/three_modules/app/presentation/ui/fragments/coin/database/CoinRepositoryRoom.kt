package com.example.three_modules.app.presentation.ui.fragments.coin.database

import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel

interface CoinRepositoryRoom {

    suspend fun insert(coins: MutableList<CoinRVItemModel>)

    suspend fun getAllCoins(): MutableList<CoinRVItemModel>
}