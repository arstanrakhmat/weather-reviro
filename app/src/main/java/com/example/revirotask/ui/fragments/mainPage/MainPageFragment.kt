package com.example.revirotask.ui.fragments.mainPage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.revirotask.R
import com.example.revirotask.databinding.FragmentMainPageBinding
import com.example.revirotask.model.Favorite
import com.example.revirotask.model.mapHourlyToFavHourly
import com.example.revirotask.ui.fragments.BaseFragment
import com.example.revirotask.utils.Resource
import com.example.revirotask.utils.checkForInternet
import com.example.revirotask.utils.filterHourlyList
import com.example.revirotask.viewModel.FavoriteViewModel
import com.example.revirotask.viewModel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainPageFragment : BaseFragment<FragmentMainPageBinding>() {

    private lateinit var favoritesAdapter: FavoritesAdapter
    private val favoriteViewModel by viewModels<FavoriteViewModel>()
    private val weatherViewModel by viewModels<WeatherViewModel>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainPageBinding {
        return FragmentMainPageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        setupObserver()
        clickListener()
    }

    private fun clickListener() {
        binding.apply {
            btnAddCity.setOnClickListener {
                findNavController().navigate(R.id.searchFragment)
            }

            favoritesAdapter.setOnDeleteClickListener { favorite ->
                favoriteViewModel.deleteFavorite(favorite)
                Toast.makeText(requireContext(), "Deleted Successfully", Toast.LENGTH_LONG)
                    .show()
            }

            favoritesAdapter.setOnFavoriteClickListener {
                findNavController().navigate(R.id.weatherDetailsFragment)
            }

            refreshRv.setOnRefreshListener {
                refreshDataOfFavorites()
            }

            btnMenu.setOnClickListener {
                findNavController().navigate(R.id.weatherDetailsFragment)
            }
        }
    }

    private fun setupRv() {
        favoritesAdapter = FavoritesAdapter()
        binding.rvChosenWeathers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoritesAdapter
        }


        viewLifecycleOwner.lifecycleScope.launch {
            favoriteViewModel.favList.collect { listOfWeatherValues ->
                favoritesAdapter.differ.submitList(listOfWeatherValues)
            }
        }
    }

    private fun refreshDataOfFavorites() {
        if (checkForInternet(requireContext())) {
            val favList = favoriteViewModel.favList.value
            for (fav in favList) {
                weatherViewModel.getWeatherData(fav.latitude, fav.longitude)
            }
            binding.refreshRv.isRefreshing = false
        } else {
            Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT).show()
            binding.refreshRv.isRefreshing = false
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

                        favoriteViewModel.updateFavorite(newFavorite)
                    }
                }

                is Resource.Error -> {
                    response.message?.let { message ->
                        Toast.makeText(requireContext(), "Error: $message", Toast.LENGTH_LONG)
                            .show()
                        Log.d("UPDATE_ERROR", "setupObserver: $message")
                    }
                }

                is Resource.Loading -> {
                }
            }
        }
    }
}