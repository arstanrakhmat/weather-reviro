package com.example.revirotask.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.revirotask.databinding.FragmentSearchBinding
import com.example.revirotask.model.City
import com.example.revirotask.ui.fragments.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private lateinit var cityAdapter: CityAdapter

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        clickListeners()
    }

    private fun clickListeners() {
        binding.apply {
            btnGoBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        cityAdapter.setOnAddToFavoriteClickListener { cityInfo ->
            Toast.makeText(requireContext(), cityInfo.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRv() {
        val cities = listOf(
            City("Tokyo", 35.6895, 139.6917),
            City("New York", 40.7128, -74.0060),
            City("Paris", 48.8566, 2.3522),
            City("London", 51.5074, -0.1278),
            City("Beijing", 39.9042, 116.4074),
        )
        cityAdapter = CityAdapter(cities)

        binding.rvCity.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cityAdapter
        }
    }

    /*
    data class Weather(val cityName: String, /* other properties */)

    // List of favorite Weather objects
    val favoriteWeathers: List<Weather> = // Get your list of favorite Weather objects

    // List of city names from the search screen
    val searchResults: List<String> = // Get your list of search results

    // Filter out favorite cities from the search results
    val filteredSearchResults = searchResults.filter { cityName ->
        !favoriteWeathers.any { it.cityName == cityName }
    }

    */

}