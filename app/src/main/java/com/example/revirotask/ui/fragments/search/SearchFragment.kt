package com.example.revirotask.ui.fragments.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.revirotask.R
import com.example.revirotask.databinding.FragmentSearchBinding
import com.example.revirotask.model.Favorite
import com.example.revirotask.model.Recent
import com.example.revirotask.model.mapHourlyToFavHourly
import com.example.revirotask.ui.fragments.BaseFragment
import com.example.revirotask.utils.Constants
import com.example.revirotask.utils.Resource
import com.example.revirotask.utils.checkForInternet
import com.example.revirotask.utils.filterHourlyList
import com.example.revirotask.viewModel.FavoriteViewModel
import com.example.revirotask.viewModel.WeatherViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private lateinit var cityAdapter: CityAdapter
    private val weatherViewModel by viewModels<WeatherViewModel>()
    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        setupChipGroup()
        setupObserver()
        setupSearchView()
        clickListeners()
    }

    private fun clickListeners() {
        binding.apply {
            btnGoBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        cityAdapter.setOnAddToFavoriteClickListener { cityInfo ->
            if (checkForInternet(requireContext())) {
                weatherViewModel.getWeatherData(
                    cityInfo.latitude.toString(),
                    cityInfo.longitude.toString()
                )
            } else {
                Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupObserver() {
        weatherViewModel.weatherData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { weather ->
                        val hourlyListFilteredFirst5 = filterHourlyList(weather.hourly)
                        val favHourlyList = mapHourlyToFavHourly(hourlyListFilteredFirst5)
                        val cityS = weather.timezone.split("/")[1]
                        val newFavorite = Favorite(
                            city = cityS,
                            latitude = weather.lat.toString(),
                            longitude = weather.lon.toString(),
                            degree = weather.current.temp.toInt(),
                            dt = weather.current.dt,
                            weatherDescription = weather.current.weather[0].description,
                            weatherId = weather.current.weather[0].id,
                            uvIndex = weather.current.uvi,
                            wind = weather.current.wind_speed,
                            humidity = weather.current.humidity,
                            hourlyList = favHourlyList
                        )

                        favoriteViewModel.insertFavorite(newFavorite)
                        favoriteViewModel.insertToRecent(Recent(cityS))
                        hideProgressBar()
                    }
                    findNavController().popBackStack()

                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(requireContext(), "Error: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun setupRv() {
        cityAdapter = CityAdapter(Constants.CITIES)

        binding.rvCity.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cityAdapter
        }
    }

    private fun setupSearchView() {
        binding.citySearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                cityAdapter.getFilter().filter(query)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                cityAdapter.getFilter().filter(p0)
                return true
            }
        })
    }

    private fun setupChipGroup() {
        viewLifecycleOwner.lifecycleScope.launch {
            favoriteViewModel.recentList.collect { listOfRecent ->
                if (listOfRecent.isEmpty()) {
                    hideChipGroup()
                } else {
                    showChipGroup()
                    listOfRecent.forEach { recent ->
                        addChips(requireContext(), recent)
                    }
                }
            }
        }
    }

    private fun addChips(context: Context, recent: Recent) {
        val chip = Chip(context, null, R.style.CustomChipStyle)
        chip.textSize = 16F
        chip.setTextColor(ContextCompat.getColor(context, R.color.chip_text_color))
        chip.text = recent.city
        chip.isEnabled = false

        binding.chipGroup.addView(chip)
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideChipGroup() {
        binding.scrollViewForChips.visibility = View.GONE
        binding.tvPopularCities.visibility = View.GONE
    }

    private fun showChipGroup() {
        binding.scrollViewForChips.visibility = View.VISIBLE
        binding.tvPopularCities.visibility = View.VISIBLE
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