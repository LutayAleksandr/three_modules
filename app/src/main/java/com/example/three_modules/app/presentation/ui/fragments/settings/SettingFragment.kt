package com.example.three_modules.app.presentation.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.App
import com.example.three_modules.app.di.fragment.DaggerFragmentComponent
import com.example.three_modules.app.di.fragment.FragmentModule
import com.example.three_modules.app.presentation.activity.MainActivity
import com.example.three_modules.app.presentation.ui.fragments.settings.adapter.SettingRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.settings.model.SettingRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.settings.viewmodel.SettingViewModel
import com.example.three_modules.app.presentation.ui.toolbarlistener.ToolbarListenerManager
import com.example.three_modules.databinding.FragmentSettingBinding
import java.util.*
import javax.inject.Inject


class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private val settingViewModel: SettingViewModel by viewModels {
        (requireActivity().application as App).appComponent.provideViewModelFactory()
    }

    @Inject
    lateinit var toolbarListenerManager: ToolbarListenerManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DaggerFragmentComponent.builder()
            .activityComponent((activity as MainActivity).activityComponent)
            .fragmentModule(FragmentModule(fragment = this))
            .build().inject(this)
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarListenerManager.changeToolbarButtonState(isSaveButton = true)
        settingViewModel.getAllModules()
        settingViewModel.buildList()
        lifecycleScope.launchWhenResumed {
            settingViewModel.list.collect { list ->
                setupRecyclerView(list = list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

     private fun setupRecyclerView(list: List<SettingRVItemModel>) {

        val settingRVAdapter = SettingRVAdapter(
            list
        )

        binding.fsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.fsRecyclerView.adapter = settingRVAdapter
        binding.fsRecyclerView.setHasFixedSize(true)

        val itemTouchHelper by lazy {
            val simpleCallback = object : ItemTouchHelper.SimpleCallback(
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

                    Collections.swap(list, startPosition, endPosition)
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