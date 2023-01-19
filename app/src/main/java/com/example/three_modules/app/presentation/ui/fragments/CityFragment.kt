package com.example.three_modules.app.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.CityRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.SettingRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityItemType
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.SettingRVItemModel
import com.example.three_modules.databinding.FragmentSettingBinding
import com.example.three_modules.databinding.FragmentTownBinding

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
        val recyclerViewList = listOf(
            CityRVItemModel(
                cityName = "Таганрог",
                countryName = "Ru",
                color = ContextCompat.getColor(requireContext(), R.color.blue),
                itemType = CityItemType.ADD
            ),
            CityRVItemModel(
                cityName = "Ростов-на-дону",
                countryName = "Ru",
                color = ContextCompat.getColor(requireContext(), R.color.lightBlue),
                itemType = CityItemType.ADD
            ),

        )
        val cityRVAdapter = CityRVAdapter(
            recyclerViewList
        )

        binding.ftRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.ftRecyclerView.adapter = cityRVAdapter
    }


}

