package com.example.revirotask.ui.fragments.weatherDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.revirotask.databinding.FragmentWeatherDetailsItemBinding
import com.example.revirotask.ui.fragments.BaseFragment

class WeatherDetailsItemFragment(private val weatherDetail: String) :
    BaseFragment<FragmentWeatherDetailsItemBinding>() {
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWeatherDetailsItemBinding {
        return FragmentWeatherDetailsItemBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCityName.text = weatherDetail
    }

}