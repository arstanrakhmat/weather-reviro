package com.example.revirotask.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.revirotask.databinding.FragmentWeatherDetailsBinding


class WeatherDetailsFragment : BaseFragment<FragmentWeatherDetailsBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWeatherDetailsBinding {
        return FragmentWeatherDetailsBinding.inflate(inflater, container, false)
    }

}