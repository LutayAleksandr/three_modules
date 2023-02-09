package com.example.three_modules.app.presentation.ui.fragments.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.three_modules.app.App
import com.example.three_modules.app.presentation.ui.fragments.weather.adapters.WeatherRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.weather.viewmodel.WeatherViewModel
import com.example.three_modules.databinding.FragmentWeatherBinding
import kotlinx.coroutines.launch

class WeatherFragment: Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private var rvAdapter = WeatherRVAdapter()

    private val weatherViewModel: WeatherViewModel by viewModels {
        (requireActivity().application as App).appComponent.provideViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView()
        weatherViewModel.getAllCities()
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            weatherViewModel.weathers.collect { list ->
                rvAdapter.submitList(list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupRecyclerView() {
        rvAdapter.click = { item ->
            binding.fwTextView.text = item.cityName
            weatherViewModel.selectedModel(item)
        }
        binding.fwRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.fwRecyclerView.adapter = rvAdapter
    }
}