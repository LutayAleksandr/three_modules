package com.example.three_modules.app.presentation.ui.fragments.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.three_modules.app.App
import com.example.three_modules.app.presentation.ui.fragments.weather.adapters.WeatherRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.weather.states.WeatherActions
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
        setupActions()
        weatherViewModel.getAllCities()
        rvAdapter.click = { item, position ->
            weatherViewModel.selectedModel(item)
        }
        binding.fwSaveButton.setOnClickListener {
            lifecycleScope.launch {
                weatherViewModel.saveSelectedCity()
            }
        }
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            weatherViewModel.cities.collect { state ->
                rvAdapter.submitList(state)
                rvAdapter.notifyItemRangeChanged(0, state.size)
            }
        }
        lifecycleScope.launch {
            weatherViewModel.selectedTitle.collect { title ->
                binding.fwTextView.text = title
            }
        }
    }

    private fun setupActions() = lifecycleScope.launchWhenStarted {
        weatherViewModel.action.collect {
            if (it is WeatherActions.PopBackStack) {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupRecyclerView() {
        binding.fwRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.fwRecyclerView.adapter = rvAdapter
    }
}