package com.example.three_modules.app.presentation.ui.fragments.city

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.three_modules.app.App
import com.example.three_modules.app.presentation.ui.fragments.city.adapters.CityRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.city.viewmodel.CityViewModel
import com.example.three_modules.databinding.FragmentTownBinding
import kotlinx.coroutines.launch

class CityFragment : Fragment() {

    private var _binding: FragmentTownBinding? = null
    private val binding get() = _binding!!
    private var rvAdapter = CityRVAdapter()

    private val cityViewModel: CityViewModel by viewModels {
        (requireActivity().application as App).appComponent.provideViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTownBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView()
        cityViewModel.getAllCities()
        rvAdapter.click = { item, position ->
            cityViewModel.selectedModel(item)
        }

        binding.ftSaveButton.setOnClickListener {
            lifecycleScope.launch {
                cityViewModel.saveSelectedCity()
            }
        }
    }

    private fun setupViewModel() {
        lifecycleScope.launch {
            cityViewModel.cities.collect { state ->
                Log.d("LOG_TEST", "cityList updated")
                rvAdapter.submitList(state)
                rvAdapter.notifyItemRangeChanged(0, state.size)
            }
        }
        lifecycleScope.launch {
            cityViewModel.selectedTitle.collect { title ->
                binding.ftTextView.text = title
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupRecyclerView() {
        binding.ftRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.ftRecyclerView.adapter = rvAdapter
    }
}

