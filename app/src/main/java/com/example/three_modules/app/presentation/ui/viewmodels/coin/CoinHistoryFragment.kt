package com.example.three_modules.app.presentation.ui.viewmodels.coin

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.three_modules.app.App
import com.example.three_modules.app.presentation.ui.fragments.coin.viewmodel.CoinViewModel
import com.google.android.material.snackbar.Snackbar

class CoinHistoryFragment: Fragment() {

    private val sharedViewModel: CoinViewModel by activityViewModels {
        (requireActivity().application as App).appComponent.provideViewModelFactory()
    }

//    private fun setupUI() {
//        subscribeToSharedViewEffects()
//    }
//
//        private fun subscribeToSharedViewEffects() {
//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            sharedViewModel.sharedViewEffects.collect {
//                when (it) {
////                    is SharedViewEffects.PriceVariation -> notifyOfPriceVariation(it.variation)
//                }
//            }
//        }
//    }

    private fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }
}