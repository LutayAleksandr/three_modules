package com.example.three_modules.app.presentation.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.R
import com.example.three_modules.app.presentation.ui.fragments.settings.adapter.SettingRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.settings.model.SettingRVItemModel
import com.example.three_modules.databinding.FragmentSettingBinding
import java.util.*


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
        val recyclerViewList = mutableListOf(
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
        /*for (element in recyclerViewList) {
            if (element)
                ContextCompat.getColor(requireContext(), R.color.blue)
            else
                ContextCompat.getColor(requireContext(), R.color.lightBlue)
        }*/


        val settingRVAdapter = SettingRVAdapter(
            recyclerViewList
        )

        binding.fsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.fsRecyclerView.adapter = settingRVAdapter

        val itemTouchHelper by lazy {
            var simpleCallback = object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),
                0
            ) {  // drag and drop
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    val startPosition = viewHolder.absoluteAdapterPosition
                    val endPosition = target.absoluteAdapterPosition

                    Collections.swap(recyclerViewList, startPosition, endPosition)
                    recyclerView.adapter?.notifyItemMoved(startPosition, endPosition)
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                }
            }
            ItemTouchHelper(simpleCallback)
        }
        itemTouchHelper.attachToRecyclerView(binding.fsRecyclerView)
    }

}