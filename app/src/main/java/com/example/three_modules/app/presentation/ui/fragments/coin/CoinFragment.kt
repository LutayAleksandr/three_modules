package com.example.three_modules.app.presentation.ui.fragments.coin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.three_modules.app.App
import com.example.three_modules.app.presentation.ui.fragments.coin.adapters.CoinRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.coin.models.toCoinEntityFromItem
import com.example.three_modules.app.presentation.ui.fragments.coin.viewmodel.CoinViewModel
import com.example.three_modules.databinding.FragmentCoinBinding
import kotlinx.coroutines.launch

class CoinFragment : Fragment() {

    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!!
    private var rvAdapter = CoinRVAdapter()

    private val coinViewModel: CoinViewModel by activityViewModels {
        (requireActivity().application as App).appComponent.provideViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupViewModel()
        coinViewModel.getAllCoin()
        rvAdapter.click = { item, position ->
            coinViewModel.countSelectedCoin(item)
            rvAdapter.notifyItemChanged(position)
        }
//        retrieveCoins()
        binding.fcSaveButton.setOnClickListener{
            lifecycleScope.launch{
                val coins =
                    coinViewModel.coinsList.mapIndexed { _, coinRVItemModel -> coinRVItemModel.toCoinEntityFromItem() }
                        .toMutableList()
                coinViewModel.saveSelectedCoins()
            }
        }
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            coinViewModel.coins.collect { list ->
                binding.fcProgressBar.visibility = View.GONE
                rvAdapter.submitList(list)
                rvAdapter.notifyItemRangeChanged(0, list.size)
            }
        }
        lifecycleScope.launch {
            coinViewModel.selectedTitle.collect { title ->
                binding.fcTextView1.text = title
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupUI() {
        binding.fcRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.fcRecyclerView.adapter = rvAdapter
        binding.fcRecyclerView.setHasFixedSize(true)
    }

//    private fun insertCoins(coins: MutableList<CoinRVItemModel>){
//        lifecycleScope.launch(Dispatchers.IO){
//            (context?.applicationContext as App).repositoryRoom.insert(coins = coins)
//        }
//    }
//
//    private fun retrieveCoins(){
//        lifecycleScope.launch(Dispatchers.IO) {
//            val coins = (context?.applicationContext as App).repositoryRoom.getAllCoins()
//            withContext(Dispatchers.IO) {
//                coinViewModel.setCoins(coins)
//            }
//        }
//    }


}

