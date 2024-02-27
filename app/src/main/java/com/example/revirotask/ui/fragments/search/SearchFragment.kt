package com.example.revirotask.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.revirotask.databinding.FragmentSearchBinding
import com.example.revirotask.model.Favorite
import com.example.revirotask.model.Hourly
import com.example.revirotask.model.mapHourlyToFavHourly
import com.example.revirotask.ui.fragments.BaseFragment
import com.example.revirotask.utils.Constants
import com.example.revirotask.utils.Resource
import com.example.revirotask.viewModel.FavoriteViewModel
import com.example.revirotask.viewModel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

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
            weatherViewModel.getWeatherData(
                cityInfo.latitude.toString(),
                cityInfo.longitude.toString()
            )
        }
    }

    private fun setupObserver() {
        weatherViewModel.weatherData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { weather ->
                        Toast.makeText(requireContext(), "Data received", Toast.LENGTH_SHORT).show()
                        val hourlyListFilteredFirst5 = filterHourlyList(weather.hourly)
                        val favHourlyList = mapHourlyToFavHourly(hourlyListFilteredFirst5)
                        val newFavorite = Favorite(
                            city = weather.timezone.split("/")[1],
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

        Toast.makeText(requireContext(), "${favoriteViewModel.favList.value.size}", Toast.LENGTH_SHORT)
            .show()

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

    private fun filterHourlyList(inputList: List<Hourly>): List<Hourly> {
        val indicesToInclude = listOf(0, 2, 4, 6, 8)

        return inputList.filterIndexed { index, _ -> index in indicesToInclude }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
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