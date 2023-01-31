package com.example.three_modules.app.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.three_modules.app.extensions.getTownsFromAssets
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.CityRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityJsonModel
import com.example.three_modules.databinding.FragmentTownBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CityFragment : Fragment() {

    private var _binding: FragmentTownBinding? = null
    private val binding get() = _binding!!

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
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupRecyclerView() {
        val cityRVAdapter = CityRVAdapter(
            cityRVItemModelList = requireContext().getTownsFromAssets()
        )
        cityRVAdapter.click = { item ->
            binding.ftTextView.text = item.cityName
        }
        binding.ftRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.ftRecyclerView.adapter = cityRVAdapter
    }
}

