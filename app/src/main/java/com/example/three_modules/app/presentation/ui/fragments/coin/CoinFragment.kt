package com.example.three_modules.app.presentation.ui.fragments.coin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.three_modules.app.App
import com.example.three_modules.app.presentation.ui.fragments.coin.adapters.CoinRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.coin.viewmodel.CoinViewModel
import com.example.three_modules.databinding.FragmentCoinBinding
import com.example.three_modules.utils.Status
import kotlinx.coroutines.launch

class CoinFragment : Fragment() {
    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!!
    private var rvAdapter = CoinRVAdapter()

//  private val viewModel by viewModels<CoinViewModel>()

    private val coinViewModel: CoinViewModel by viewModels {
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
        setupObservers()
        coinViewModel.getAllCoin()
        rvAdapter.click = { item ->
            binding.fcTextView1.text = item.name
            coinViewModel.selectedModel(item)
        }
    }
    private fun setupViewModel() {
        lifecycleScope.launch {
            coinViewModel.coins.collect { list ->
                rvAdapter.submitList(list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupUI() {
        binding.fcRecyclerView.layoutManager = LinearLayoutManager(context)
//        binding.fcRecyclerView.addItemDecoration( //бесячая полоска между элементами списка
//            DividerItemDecoration(
//                fcRecyclerView.context,
//                (fcRecyclerView.layoutManager as LinearLayoutManager).orientation
//            )
//        )
        binding.fcRecyclerView.adapter = rvAdapter
    }

    private fun setupObservers() {
        coinViewModel.getCoin().observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.fcRecyclerView.visibility = View.VISIBLE
                        binding.fcProgressBar.visibility = View.GONE
                        resource.data?.let { coins -> retrieveList(coins) }
                    }
                    Status.ERROR -> {
                        binding.fcRecyclerView.visibility = View.VISIBLE
                        binding.fcProgressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.fcProgressBar.visibility = View.VISIBLE
                        binding.fcRecyclerView.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun retrieveList(coins: List<CoinRVItemModel>) {
        rvAdapter.addCoins(coins = coins)
    }

}

