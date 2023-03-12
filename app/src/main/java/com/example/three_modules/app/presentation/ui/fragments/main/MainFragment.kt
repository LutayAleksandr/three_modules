package com.example.three_modules.app.presentation.ui.fragments.main

import android.annotation.SuppressLint
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
import com.example.three_modules.app.di.fragment.DaggerFragmentComponent
import com.example.three_modules.app.di.fragment.FragmentModule
import com.example.three_modules.app.presentation.activity.MainActivity
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.mainadapter.MainRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.main.viewmodel.MainViewModel
import com.example.three_modules.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels {
        (requireActivity().application as App).appComponent.provideViewModelFactory()
    }

    private var rvAdapter = MainRVAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerFragmentComponent.builder()
            .activityComponent((activity as MainActivity).activityComponent)
            .fragmentModule(FragmentModule(fragment = this))
            .build().inject(this)
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
        setupUI()
        setupRecyclerView()
        lifecycleScope.launch{
            mainViewModel.buildList()
            setupRecyclerView()
        }

    }

    private fun setupUI(){
        lifecycleScope.launchWhenResumed {
            mainViewModel.loadSettingList()
            mainViewModel.getSelectedCoins()
            mainViewModel.getSelectedCityForWeather()
            mainViewModel.getCoordinates()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView() {
        Log.d("LOG_TEST", "called")


        lifecycleScope.launch {
            mainViewModel.list.collect { list ->
                if (list.isNotEmpty()) {
                    rvAdapter.submitList(null)
                    rvAdapter.submitList(list)
                    rvAdapter.notifyDataSetChanged()
                } else {
                    binding.fmProgressBar.visibility = View.VISIBLE
                }
            }
        }


        rvAdapter.click = { itemType,  ->
            when (itemType) {
                MainItemType.CITY -> findNavController().navigate(R.id.action_mainFragment_to_cityFragment)
                MainItemType.COIN -> findNavController().navigate(R.id.action_mainFragment_to_coinFragment)
                MainItemType.WEATHER -> findNavController().navigate(R.id.action_mainFragment_to_weatherFragment2)
            }
        }

        rvAdapter.clickReplace = {itemType ->
            when (itemType) {
                MainItemType.COIN -> {
                    setupRecyclerView()
                }
                MainItemType.WEATHER -> {
                    setupRecyclerView()
                }
                MainItemType.CITY -> setupUI()
            }
        }

        binding.fmRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.fmRecyclerView.adapter = rvAdapter
    }
}

//        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(Runnable {
//        }, 0, 5, TimeUnit.SECONDS)