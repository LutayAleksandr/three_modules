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
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.main.viewmodel.MainViewModel
import com.example.three_modules.databinding.FragmentMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds


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
        lifecycleScope.launch{
            mainViewModel.loadSettingList()
            mainViewModel.buildList()
            setupRecyclerView()
        }
        tickerFlow(5.seconds)
            .map { LocalDateTime.now() }
            .distinctUntilChanged { old, new ->
                old.second == new.second
            }
            .onEach {
                mainViewModel.getSelectedCoins()
                mainViewModel.buildList()
            }
            .launchIn(lifecycleScope)
    }
    fun tickerFlow(period: Duration, initialDelay: Duration = Duration.ZERO) = flow {
        delay(initialDelay)
        while (true) {
            emit(Unit)
            delay(period)
        }
    }



    private fun setupUI(){
        lifecycleScope.launchWhenResumed {
            mainViewModel.getSelectedCoins()
            mainViewModel.getSelectedCityForWeather()
            mainViewModel.getCoordinates()
        }
        lifecycleScope.launch {
            mainViewModel.list.collect { list ->
                if (list.isNotEmpty()) {
                    rvAdapter.submitList(list)
//                    rvAdapter.notifyDataSetChanged()
                } else {
                    binding.fmProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView() {
        Log.d("LOG_TEST", "called")

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