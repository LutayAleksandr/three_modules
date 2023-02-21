package com.example.three_modules.app.presentation.ui.fragments.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.three_modules.R
import com.example.three_modules.app.App
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.coin.viewmodel.CoinViewModel
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.mainadapter.MainRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.main.viewmodel.MainViewModel
import com.example.three_modules.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()

    private val coinViewModel: CoinViewModel by activityViewModels {
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
        setupRecyclerView()
        retrieveCoins()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
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
                coins = coinViewModel.selectedCoinList
            )
        )
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

    private fun retrieveCoins(){
        lifecycleScope.launch(Dispatchers.IO) {
            val coins = (context?.applicationContext as App).repositoryRoom.getAllCoins()
            withContext(Dispatchers.IO) {
                coinViewModel.setCoins(coins)
            }
        }
    }

    private fun setupTestCoinsList(): List<CoinRVItemModel> {
        return listOf(
            CoinRVItemModel(
                id = 1,
                name = "Bitcoin",
                imageUrl = "",
                current_price = "25435.23".toFloat(),
                price_change_24h = "100.233".toFloat(),
                color = Color.BLUE
            ),
            CoinRVItemModel(
                id = 1,
                name = "Bitcoin",
                imageUrl = "",
                current_price = "25435.23".toFloat(),
                price_change_24h = "100.233".toFloat(),
                color = Color.BLUE
            ),
            CoinRVItemModel(
                id = 1,
                name = "Bitcoin",
                imageUrl = "",
                current_price = "25435.23".toFloat(),
                price_change_24h = "100.233".toFloat(),
                color = Color.BLUE
            ),
        )
    }
}