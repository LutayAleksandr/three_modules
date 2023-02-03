package com.example.three_modules.app.presentation.ui.viewmodels.coin

open class SharedViewEffects {
    data class PriceVariation(val variation: Int): SharedViewEffects()
}