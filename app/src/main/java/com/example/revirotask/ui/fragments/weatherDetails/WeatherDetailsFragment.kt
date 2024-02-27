package com.example.revirotask.ui.fragments.weatherDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.revirotask.databinding.FragmentWeatherDetailsBinding
import com.example.revirotask.model.Favorite
import com.example.revirotask.ui.fragments.BaseFragment
import com.example.revirotask.viewModel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class WeatherDetailsFragment : BaseFragment<FragmentWeatherDetailsBinding>() {

    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWeatherDetailsBinding {
        return FragmentWeatherDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            favoriteViewModel.favList.collect { listOfWeatherValues ->
                setupViewPager(listOfWeatherValues)
            }
        }
    }

    private fun setupViewPager(list: List<Favorite>) {
        val vpAdapter = WeatherDetailsViewPagerAdapter(
            list,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPagerAdapter.adapter = vpAdapter
        binding.dotsIndicator.attachTo(binding.viewPagerAdapter)
    }

}