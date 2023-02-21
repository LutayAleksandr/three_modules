package com.example.three_modules.app.presentation.ui.fragments.coin.database

import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel

class CoinRepositoryImpl(private val coinsDao: CoinsDao): CoinRepositoryRoom {
    override suspend fun insert(coins: MutableList<CoinRVItemModel>) {
        coinsDao.insert(coins)
    }

    override suspend fun getAllCoins() = coinsDao.getAllCoins()
}