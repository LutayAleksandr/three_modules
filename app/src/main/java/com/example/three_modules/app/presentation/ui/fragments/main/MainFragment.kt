package com.example.three_modules.app.presentation.ui.fragments.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.three_modules.R
import com.example.three_modules.app.App
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.mainadapter.MainRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.main.viewmodel.MainViewModel
import com.example.three_modules.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels {
        (requireActivity().application as App).appComponent.provideViewModelFactory()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            lifecycleScope.launchWhenStarted {
                mainViewModel.coins.collect {
                    setupRecyclerView(list = it)
                }
            }

        } catch (e: Exception) {

        }
        mainViewModel.getSelectedCoins()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun setupRecyclerView(list: List<CoinRVItemModel>) {
        val recyclerViewList = listOf(

            DataModel.HeaderRVItemModel(
                title = "Погода"
            ),
            DataModel.MainRVItemModel(
                buttonText = "Выбрать город",
                itemType = MainItemType.WEATHER
            ),
            DataModel.HeaderRVItemModel(
                title = "Город"
            ),
            DataModel.MainRVItemModel(
                buttonText = "Выбрать город",
                itemType = MainItemType.CITY
            ),
            DataModel.HeaderRVItemModel(
                title = "Курс криптовалют"
            ),
            DataModel.MainCoinRVItemModel(
                buttonText = "Выбрать криптовалюту",
                itemType = MainItemType.COIN,
                coins = list
            )
        )
        Log.d("LOG_TEST", "called")
        val mainRVAdapter = MainRVAdapter(
            recyclerViewList
        )

        mainRVAdapter.click = { itemType ->
            when (itemType) {
                MainItemType.CITY -> findNavController().navigate(R.id.action_mainFragment_to_cityFragment)
                MainItemType.COIN -> findNavController().navigate(R.id.action_mainFragment_to_coinFragment)
                MainItemType.WEATHER -> findNavController().navigate(R.id.action_mainFragment_to_weatherFragment2)
            }
        }

        binding.fmRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.fmRecyclerView.adapter = mainRVAdapter
    }
}