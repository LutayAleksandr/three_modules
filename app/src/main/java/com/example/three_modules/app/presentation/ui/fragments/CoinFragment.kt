package com.example.three_modules.app.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.CoinRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.retrofit.Common
import com.example.three_modules.app.presentation.ui.retrofit.RetrofitService
import com.example.three_modules.databinding.FragmentCoinBinding
import kotlinx.android.synthetic.main.fragment_coin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CoinFragment : Fragment() {
    private var _binding: FragmentCoinBinding? = null
    private val binding get() = _binding!!

    private val coinList: MutableList<CoinRVItemModel> = mutableListOf()
    private lateinit var coinAdapter: CoinRVAdapter

    lateinit var coinService: RetrofitService
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: CoinRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinBinding.inflate(inflater, container, false)

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

    private fun setupRecyclerView(){
        coinService = Common.retrofitService
        fcRecyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        fcRecyclerView.layoutManager = layoutManager
        getAllCoinList()

        binding.fcRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.fcRecyclerView.adapter = CoinRVAdapter(coinList)
    }
    private fun getAllCoinList() {
        coinService.getCoinList().enqueue(object : Callback<MutableList<CoinRVItemModel>> {
            override fun onResponse(
                call: Call<MutableList<CoinRVItemModel>>,
                response: Response<MutableList<CoinRVItemModel>>
            ) {
                adapter = CoinRVAdapter(response.body() as MutableList<CoinRVItemModel>)
                adapter.notifyDataSetChanged()
                fcRecyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<MutableList<CoinRVItemModel>>, t: Throwable) {

            }

        })
    }

}