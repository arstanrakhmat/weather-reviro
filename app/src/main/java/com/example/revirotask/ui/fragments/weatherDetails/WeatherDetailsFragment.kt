package com.example.revirotask.ui.fragments.weatherDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.revirotask.databinding.FragmentWeatherDetailsBinding
import com.example.revirotask.ui.fragments.BaseFragment


class WeatherDetailsFragment : BaseFragment<FragmentWeatherDetailsBinding>() {

    companion object {
        val citiesList = listOf(
            "Bishkek",
            "Osh",
            "Moscow",
            "Astana",
            "New-York",
            "Washington",
            "Dysney",
            "Virginia",
        )
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWeatherDetailsBinding {
        return FragmentWeatherDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {

        val vpAdapter = WeatherDetailsViewPagerAdapter(
            citiesList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPagerAdapter.adapter = vpAdapter
        binding.dotsIndicator.attachTo(binding.viewPagerAdapter)
    }

}