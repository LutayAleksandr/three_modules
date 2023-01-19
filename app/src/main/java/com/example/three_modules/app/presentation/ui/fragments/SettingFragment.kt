package com.example.three_modules.app.presentation.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.SettingRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.SettingRVItemModel
import com.example.three_modules.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
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
            SettingRVItemModel(
                textModules = "Погода",
                color = ContextCompat.getColor(requireContext(), R.color.blue)
            ),
            SettingRVItemModel(
                textModules = "Город",
                color = ContextCompat.getColor(requireContext(), R.color.lightBlue)
            ),
            SettingRVItemModel(
                textModules = "Курс Криптовалют",
                color = ContextCompat.getColor(requireContext(), R.color.blue)
            )
        )
        val settingRVAdapter = SettingRVAdapter(
            recyclerViewList
        )

        binding.fsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.fsRecyclerView.adapter = settingRVAdapter


    }
}