package com.example.three_modules.app.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.three_modules.app.presentation.ui.fragments.main.adapters.CityRVAdapter
import com.example.three_modules.app.presentation.ui.fragments.main.models.CityJsonModel
import com.example.three_modules.databinding.FragmentTownBinding
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

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
        /*val recyclerViewList = listOf(
            CityRVItemModel(
                cityName = "Таганрог",
                countryName = "Ru",
                color = ContextCompat.getColor(requireContext(), R.color.blue),
                id = 1
            ),
            CityRVItemModel(
                cityName = "Ростов-на-дону",
                countryName = "Ru",
                color = ContextCompat.getColor(requireContext(), R.color.lightBlue),
                id = 2
            ),
        )*/

        val citiesList: ArrayList<CityJsonModel> = ArrayList()
        try {

            val obj = JSONObject(getJSONFromAssets()!!)
            val citiesArray = obj.getJSONArray("cities")
            for (i in 0 until citiesArray.length()) {

                val city = citiesArray.getJSONObject(i)

                val id = city.getInt("id")
                val cityName = city.getString("cityName")
                val countryName = city.getString("countryName")

                val cityDetails =
                    CityJsonModel(id, cityName, countryName)

                citiesList.add(cityDetails)


            }
        }catch (e: JSONException) {
            e.printStackTrace()
        }

        val cityRVAdapter = CityRVAdapter(
            citiesList
        )

        cityRVAdapter.click = { item ->
            binding.ftTextView.text = item.cityName
        }


        binding.ftRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.ftRecyclerView.adapter = CityRVAdapter(citiesList)

        /*val cityRVAdapter = CityRVAdapter(
            recyclerViewList
        )

        cityRVAdapter.click = { item ->
            binding.ftTextView.text = item.cityName
        }

        binding.ftRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.ftRecyclerView.adapter = cityRVAdapter*/

    }
    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val citiesJSONFile = assets.open("towns.json")
            val size = citiesJSONFile.available()
            val buffer = ByteArray(size)
            citiesJSONFile.read(buffer)
            citiesJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

}

