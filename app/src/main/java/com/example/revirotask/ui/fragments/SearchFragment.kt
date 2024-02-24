package com.example.revirotask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.revirotask.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }

    private fun clickListeners() {
        binding.apply {
            btnGoBack.setOnClickListener {
                findNavController().popBackStack()
            }
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