package com.example.three_modules.app.presentation.ui.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.MainRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainRVItemModel
import com.example.three_modules.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

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
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        val recyclerViewList = listOf(
            MainRVItemModel(
                buttonText = "Выбрать Погода",
                itemType = MainItemType.WEATHER
            ),
            MainRVItemModel(
                buttonText = "Выбрать Город",
                itemType = MainItemType.CITY
            ),
            MainRVItemModel(
                buttonText = "Выбрать Криптовалюты",
                itemType = MainItemType.COIN
            )
        )
        val mainRVAdapter = MainRVAdapter(
            recyclerViewList
        )
        mainRVAdapter.click = { itemType ->
            when (itemType) {
                MainItemType.CITY -> findNavController().navigate(R.id.action_mainFragment_to_cityFragment)
                MainItemType.COIN -> findNavController().navigate(R.id.action_mainFragment_to_coinFragment)
                else -> {
                    Toast.makeText(requireContext(), "Will be implemented soon", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.fmRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.fmRecyclerView.adapter = mainRVAdapter
    }

}