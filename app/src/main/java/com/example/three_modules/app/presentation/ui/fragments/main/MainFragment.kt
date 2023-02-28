package com.example.three_modules.app.presentation.ui.fragments.main

import android.os.Bundle
import android.os.Handler
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
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.mainadapter.MainRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.DataModel
import com.example.three_modules.app.presentation.ui.fragments.main.models.MainItemType
import com.example.three_modules.app.presentation.ui.fragments.main.viewmodel.MainViewModel
import com.example.three_modules.databinding.FragmentMainBinding
import com.yandex.mapkit.mapview.MapView

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels {
        (requireActivity().application as App).appComponent.provideViewModelFactory()
    }
    private lateinit var mapView: MapView
    var handler: Handler = Handler()
    var runnable: Runnable? = null
    var delay = 5000


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
        lifecycleScope.launchWhenResumed {
            mainViewModel.getSelectedCoins()
            mainViewModel.list.collect { list ->
                if (list.isNotEmpty()) {
                    setupRecyclerView(list = list)
                    binding.fmProgressBar.visibility = View.GONE
                } else {
                    binding.fmProgressBar.visibility = View.VISIBLE
                }
            }
        }
//        lifecycleScope.launch {
//            try {
//                lifecycleScope.launchWhenResumed {
//                    mainViewModel.getSelectedCoins()
//                    mainViewModel.coins.collect {
//                        mainViewModel.getSelectedCoins()
//                        setupRecyclerView(list = it)
//                    }
//                }
//
//            } catch (e: Exception) {
//                Toast.makeText(context, "При загрузке произошла ошибка", Toast.LENGTH_SHORT).show()
//
//                val repeat = getView()?.findViewById<View>(R.id.imcrCardView)
//
//                repeat?.setOnClickListener {
//                    mainViewModel.getSelectedCoins()
//                    lifecycleScope.launchWhenStarted {
//                        mainViewModel.coins.collect {
//                            setupRecyclerView(list = it)
//                        }
//                    }
//                }
//            }
//        }
    }

//    override fun onResume() {
//        handler.postDelayed(Runnable {
//            handler.postDelayed(runnable!!, delay.toLong())
//            mainViewModel.getSelectedCoins()
//            lifecycleScope.launchWhenStarted {
//                mainViewModel.coins.collect {
//                    setupRecyclerView(list = it)
//                }
//            }
//        }.also { runnable = it }, delay.toLong())
//        super.onResume()
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(list: List<DataModel>) {
        Log.d("LOG_TEST", "called")
        val mainRVAdapter = MainRVAdapter(
            list
        )

        mainRVAdapter.click = { itemType ->
            when (itemType) {
                MainItemType.CITY -> findNavController().navigate(R.id.action_mainFragment_to_cityFragment)
                MainItemType.COIN -> findNavController().navigate(R.id.action_mainFragment_to_coinFragment)
                MainItemType.WEATHER -> findNavController().navigate(R.id.action_mainFragment_to_weatherFragment2)
            }
        }

        binding.fmRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.fmRecyclerView.adapter = mainRVAdapter
    }
}